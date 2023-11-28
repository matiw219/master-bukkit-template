package me.masterkaiser.template.user;

import eu.okaeri.persistence.document.Document;
import eu.okaeri.tasker.core.Tasker;
import me.masterkaiser.framework.Initializable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserManager implements Initializable {
    private final UserRepository userRepository;
    private final Tasker tasker;
    private final Map<UUID, User> users = new HashMap<>();
    private final Map<String, UUID> uuids = new HashMap<>();

    public UserManager(UserRepository userRepository, Tasker tasker) {
        this.userRepository = userRepository;
        this.tasker = tasker;
    }

    @Override
    public void initialize() {
        load();
    }

    public void load() {
        this.userRepository.findAll().forEach(user -> {
            this.users.put(user.getUuid(), user);
            this.uuids.put(user.getName().toLowerCase(), user.getUuid());
        });
    }

    public void create(@NotNull Player player) {
        if (this.users.containsKey(player.getUniqueId())) {
            return;
        }

        this.tasker.newSharedChain("uc/" + player.getUniqueId())
                .supplyAsync(() -> this.userRepository.create(player))
                .acceptAsync(Document::save)
                .acceptSync(user -> {
                    this.users.put(user.getUuid(), user);
                    this.uuids.put(user.getName().toLowerCase(), user.getUuid());
                })
                .abortIfException(Throwable::printStackTrace)
                .execute();
    }

    public void save(@NotNull User user) {
        this.tasker.newSharedChain("us/" + user.getUuid())
                .async(user::save)
                .abortIfException(Throwable::printStackTrace)
                .execute();
    }

    public void save(@NotNull Player player) {
        find(player).ifPresent(this::save);
    }

    public Optional<User> find(@Nullable UUID uuid) {
        if (uuid == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(users.get(uuid));
    }

    public Optional<User> find(@NotNull Player player) {
        return find(player.getUniqueId());
    }

    public Optional<User> find(@NotNull String name) {
        UUID uuid = this.uuids.getOrDefault(name.toLowerCase(), null);

        return Optional.ofNullable(uuid)
                .map(this.users::get);
    }
}
