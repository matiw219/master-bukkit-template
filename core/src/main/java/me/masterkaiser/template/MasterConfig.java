package me.masterkaiser.template;

import eu.okaeri.tasker.bukkit.BukkitTasker;
import eu.okaeri.tasker.core.Tasker;
import me.masterkaiser.command.CommandManager;
import me.masterkaiser.framework.Initializable;
import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.persistence.RepositoryFactory;
import me.masterkaiser.template.config.Commands;
import me.masterkaiser.template.config.Messages;
import me.masterkaiser.template.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MasterConfig implements Initializable {
    private final RepositoryFactory repositoryFactory;
    private final MasterPlugin masterPlugin;
    private final CommandManager commandManager;
    private final Commands commands;
    private final Messages messages;

    public MasterConfig(RepositoryFactory repositoryFactory, MasterPlugin masterPlugin, CommandManager commandManager, Commands commands, Messages messages) {
        this.repositoryFactory = repositoryFactory;
        this.masterPlugin = masterPlugin;
        this.commandManager = commandManager;
        this.commands = commands;
        this.messages = messages;
    }

    @Bean
    UserRepository userRepository() {
        return (UserRepository) this.repositoryFactory.createRepository(UserRepository.class);
    }

    @Bean
    Tasker tasker() {
        return BukkitTasker.newPool(masterPlugin);
    }

    @Override
    public void initialize() {
        this.commandManager.setPrefix(this.commands.prefix);
        this.commandManager.setNoPermissionMessage(this.messages.noPermission.findOneNotNullMessage());
        this.commandManager.setPlayerOnlyMessage(this.messages.onlyPlayerCommand.findOneNotNullMessage());
    }
}
