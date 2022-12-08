package it.founderconnessi.lib;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Classe concreta immutabile che mantiene le informazione relative al giocatore bandito.<p>
 * Lo stato è rappresentato dalla data di inizio, la motivazione e la gravità del ban.
 */
public class BanUserFields {

    /**
     * Data di inizo del ban.
     */
    public final String startDate;

    /**
     * Motivazione del ban.
     */
    public final String reason;

    /**
     * Gravità del ban.
     */
    public final Gravity gravity;

    /**
     * Costruisce un oggetto di tipo {@link BanUserFields}.
     * @param startDate data di inizio del ban, secondo lo standard ISO 8601.
     * @param reason motivazione del ban.
     * @param gravity gravità del ban.
     */
    public BanUserFields(@Nullable String startDate, @Nullable String reason, @Nullable Gravity gravity) {
        this.startDate = startDate;
        this.reason = reason;
        this.gravity = gravity;
    }

    /**
     * Metodo che permette di stostuire i placeholders, con le info corrispondenti.
     * Placeholders disponibili: %start_date%, %reason%, %gravity%
     * @param text testo contenente i placeholders.
     * @return testo dopo aver sostituito i placeholders, con le info corrispondenti.
     */
    @NotNull
    public String replacePlaceholders(@NotNull String text) {
        if (Objects.nonNull(startDate)) {
            LocalDateTime date = LocalDateTime.parse(startDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            text = text.replaceAll("%start_date%", date.format(formatter));
        }
        if(Objects.nonNull(reason))
            text = text.replaceAll("%reason%", reason);
        if(Objects.nonNull(gravity))
            text = text.replaceAll("%gravity%", gravity.toString().toLowerCase());
        return text;
    }
}
