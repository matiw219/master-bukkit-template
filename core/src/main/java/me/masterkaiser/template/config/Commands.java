package me.masterkaiser.template.config;

import eu.okaeri.configs.OkaeriConfig;
import me.masterkaiser.command.ArgumentMeta;
import me.masterkaiser.command.CommandMeta;
import me.masterkaiser.framework.yaml.YamlConfig;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@YamlConfig(fileName = "commands")
public class Commands extends OkaeriConfig {
    public String prefix = "master-bukkit-template";
    public CommandMeta testCommand = new CommandMeta(
            "testCommand",
            "/testCommand",
            "Test command",
            null,
            new String[] {"test", "test2"},
            false,
            Map.of(
                    "reload", new ArgumentMeta(
                            "reload",
                            "/test reload",
                            "Reload configs",
                            "test.command.reload",
                            null
                    )
            )
    );
}
