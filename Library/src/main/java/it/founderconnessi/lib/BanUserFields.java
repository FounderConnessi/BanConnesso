package it.founderconnessi.lib;

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

    public String replacePlaceholders(String text) {
        if (Objects.nonNull(startDate)) {
            LocalDateTime date = LocalDateTime.parse(startDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            text = text.replaceAll("%start_date%", date.format(formatter));
        }
        text = text.replaceAll("%reason%", reason)
                .replaceAll("%gravity%", gravity.toString().toLowerCase());
        return text;
    }
}
