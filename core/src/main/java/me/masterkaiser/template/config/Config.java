package me.masterkaiser.template.config;

import eu.okaeri.configs.OkaeriConfig;
import me.masterkaiser.framework.yaml.YamlConfig;
import org.springframework.stereotype.Component;

@Component
@YamlConfig(fileName = "config")
public class Config extends OkaeriConfig {
    public boolean enabled = true;
}
