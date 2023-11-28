package me.masterkaiser.template.user;

import eu.okaeri.persistence.document.Document;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Document {
    private @NotNull String name;

    public UUID getUuid() {
        return this.getPath().toUUID();
    }
}
