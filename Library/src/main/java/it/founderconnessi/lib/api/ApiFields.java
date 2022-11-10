package it.founderconnessi.lib.api;

/**
 * Classe concreta immutabile che descrive quali campi richiedere durante una richiesta API.<p>
 * La classe ha lo scopo di nascondere/mostrare determinati campi forniti dalla API.<p>
 * L'oggetto viene usato insieme a {@link ApiFilters} per comporre il corpo della richiesta ({@link ApiRequestBody}).
 */
public class ApiFields {

    /**
     * Indica se mostrare o meno il campo 'uuid'.
     */
    private final boolean uuid;

    /**
     * Indica se mostrare o meno il campo 'nickname'.
     */
    private final boolean nickname;

    /**
     * Indica se mostrare o meno il campo 'startDate'.<p>
     * Ovvero la data in cui è stato bandito il giocatore.
     */
    private final boolean startDate;

    /**
     * Indica se mostrare o meno il campo 'reason'.<p>
     * Ovvero la motivazione del ban.
     */
    private final boolean reason;

    /**
     * Indica se mostrare o meno il campo 'gravity'.
     */
    private final boolean gravity;

    /**
     * Costruisce un oggetto di tipo {@link ApiFields}.<p>
     * @param uuid true mostra campo 'uuid', false altrimenti.
     * @param nickname true mostra campo 'nickname', false altrimenti.
     * @param startDate true mostra campo 'startDate', false altrimenti.
     * @param reason true mostra campo 'reason', false altrimenti.
     * @param gravity true mostra campo 'gravity', false altrimenti.
     */
    public ApiFields(boolean uuid, boolean nickname, boolean startDate, boolean reason, boolean gravity) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.startDate = startDate;
        this.reason = reason;
        this.gravity = gravity;
    }

    /**
     * Metodo che indica se il campo uuid viene mostrato.
     * @return {@code true} se il campo uuid viene mostrato, {@code false} altrimenti.
     */
    public boolean isUuid() {
        return uuid;
    }

    /**
     * Metodo che indica se il campo nickname viene mostrato.
     * @return {@code true} se il campo nickname viene mostrato, {@code false} altrimenti.
     */
    public boolean isNickname() {
        return nickname;
    }

    /**
     * Metodo che indica se il campo startDate viene mostrato.
     * @return {@code true} se il campo startDate viene mostrato, {@code false} altrimenti.
     */
    public boolean isStartDate() {
        return startDate;
    }

    /**
     * Metodo che indica se il campo reason viene mostrato.
     * @return {@code true} se il campo reason viene mostrato, {@code false} altrimenti.
     */
    public boolean isReason() {
        return reason;
    }

    /**
     * Metodo che indica se il campo gravity viene mostrato.
     * @return {@code true} se il campo gravity viene mostrato, {@code false} altrimenti.
     */
    public boolean isGravity() {
        return gravity;
    }

    @Override
    public String toString() {
        return "\"fields\": {\"nickname\":" + nickname +
                ",\"uuid\":" +
                uuid +
                ", \"reason\":" +
                reason +
                ", \"startDate\":" +
                startDate +
                ",\"gravity\": " +
                reason +
                "}";
    }
}
