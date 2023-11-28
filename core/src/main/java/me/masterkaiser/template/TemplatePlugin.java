package me.masterkaiser.template;

import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import me.masterkaiser.framework.MasterPlugin;
import me.masterkaiser.framework.object.pair.Pair;
import me.masterkaiser.framework.persistence.EnablePersistence;
import me.masterkaiser.framework.serializer.MasterBukkitSerializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.ComponentScan;

@EnablePersistence
@ComponentScan(basePackages = "me.masterkaiser.template")
public class TemplatePlugin extends MasterPlugin {
    @Override
    public @NotNull Pair<Boolean, String> enable() {
        getContext().refresh();
        return new Pair<>(true, null);
    }

    @Override
    public OkaeriSerdesPack serializationPack() {
        return registry -> {
            registry.register(new MasterBukkitSerializer());
        };
    }
}
