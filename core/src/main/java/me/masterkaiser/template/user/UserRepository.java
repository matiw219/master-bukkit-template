package me.masterkaiser.template.user;

import eu.okaeri.persistence.repository.DocumentRepository;
import eu.okaeri.persistence.repository.annotation.DocumentCollection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@DocumentCollection(path = "user", keyLength = 36)
public interface UserRepository extends DocumentRepository<UUID, User> {
    default User create(@NotNull Player player) {
        User user = this.findOrCreateByPath(player.getUniqueId());
        user.setName(player.getName());
        return user;
    }
}
