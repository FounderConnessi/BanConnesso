package it.founderconnessi.banconnesso;

import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class BanUserFields {

    public final String startDate;
    public final String reason;
    public final Gravity gravity;

    public BanUserFields(String startDate, String reason, Gravity gravity) {
        this.startDate = startDate;
        this.reason = reason;
        this.gravity = gravity;
    }

    public TextComponent replacePlaceholders(String text) {
        LegacyComponentSerializer serializer = LegacyComponentSerializer.builder().character('&').hexCharacter('#').hexColors().build();

        if (Objects.nonNull(startDate)) {
            LocalDateTime date = LocalDateTime.parse(startDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            text = text.replaceAll("%start_date%", date.format(formatter));
        }

        return serializer.deserialize(
                text
                        .replaceAll("%reason%", reason)
                        .replaceAll("%gravity%", gravity.toString().toLowerCase())
        );
    }
}
