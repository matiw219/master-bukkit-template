package me.masterkaiser.template.config;

import eu.okaeri.configs.OkaeriConfig;
import me.masterkaiser.framework.bukkit.message.RawMessage;
import me.masterkaiser.framework.yaml.YamlConfig;
import org.springframework.stereotype.Component;

@Component
@YamlConfig(fileName = "messages")
public class Messages extends OkaeriConfig {
    public RawMessage usage = new RawMessage("&cPoprawne użycie: &6{usage}");
    public RawMessage unexpectedError = new RawMessage("&cWystąpił niespodziewany błąd");
    public RawMessage noPermission = new RawMessage("&cNie posiadasz uprawnień");
    public RawMessage onlyPlayerCommand = new RawMessage("&cTa komenda jest dostępna jedynie dla graczy.");
    public RawMessage playerOffline = new RawMessage("&cNie ma takiego gracza na serwerze");
    public RawMessage notNumber = new RawMessage("&cPodana liczba jest nieprawidłowa");
    public RawMessage userDoesNotExists = new RawMessage("&cPodany użytkownik nie istnieje");
}
