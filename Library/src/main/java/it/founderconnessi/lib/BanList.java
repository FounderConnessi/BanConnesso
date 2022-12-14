package it.founderconnessi.lib;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

/**
 * Classe concreta mutabile che rappresenta la lista degli utenti banditi.<p>
 */
public class BanList {

    /**
     * Mappa che associa il campo unico di un giocatore alle informazione del ban.<p>
     * Il campo unico può essere il nickname o il nome utente.<p>
     * Questo dipende dal valore 'online-uuid' impostato nel file di configurazione.
     */
    private final HashMap<String, BanUserFields> users;

    /**
     * Costruisce un oggetto di tipo {@link BanList}, ovvero una lista vuota.
     */
    public BanList() {
        users = new HashMap<>();
    }

    /**
     * Metodo che permette di aggiungere un utente alla lista ban.
     * @param nickname nome utente del giocatore.
     * @param startDate data di inizio del ban.
     * @param reason motivazione del ban.
     * @param gravity gravità del ban.
     */
    public void addUser(@NotNull String nickname, @Nullable String startDate, @Nullable String reason, @Nullable Gravity gravity) {
        if (!contains(nickname))
            users.put(nickname.toLowerCase(), new BanUserFields(startDate, reason, gravity));
    }

    /**
     * Metodo che permette di aggiungere un utente alla lista ban.
     * @param uuid uuid del giocatore.
     * @param startDate data di inizio del ban.
     * @param reason motivazione del ban.
     * @param gravity gravità del ban.
     */
    public void addUser(@NotNull UUID uuid, @Nullable String startDate, @Nullable String reason, @Nullable Gravity gravity) {
        addUser(uuid.toString(), startDate, reason, gravity);
    }

    /**
     * Metodo che permette di rimuovere un utente alla lista ban fornendo il nickname.
     * @param nickname nickname del giocatore.
     */
    public void removeUser(@NotNull String nickname) {
        users.remove(nickname.toLowerCase());
    }

    /**
     * Metodo che permette di rimuovere un utente alla lista ban fornendo l'uuid.
     * @param uuid uuid del giocatore.
     */
    public void removeUser(@NotNull UUID uuid) {
        removeUser(uuid.toString());
    }

    /**
     * Metodo che permette di ottenere le informazioni di un utente bandito fornendo il nickname.
     * @param nickname nickname del giocatore.
     * @return informazioni dell'utente bandito se presenti, {@code null} altrimenti.
     */
    @Nullable
    public BanUserFields getUser(@NotNull String nickname) {
        return users.get(nickname.toLowerCase());
    }

    /**
     * Metodo che permette di ottenere le informazioni di un utente bandito fornendo l'uuid.
     * @param uuid uuid del giocatore.
     * @return informazioni dell'utente bandito se presenti, {@code null} altrimenti.
     */
    @Nullable
    public BanUserFields getUser(@NotNull UUID uuid) {
        return users.get(uuid.toString());
    }

    /**
     * Metodo che permette di verificare se un utente è presente nella lista ban fornendo il nickname.
     * @param nickname nickname del giocatore.
     * @return {@code true} se il giocatore è presente, {@code false} altrimenti
     */
    public boolean contains(@NotNull String nickname) {
        return users.containsKey(nickname.toLowerCase());
    }

    /**
     * Metodo che permette di verificare se un utente è presente nella lista ban fornendo l'uuid.
     * @param uuid uuid del giocatore.
     * @return {@code true} se il giocatore è presente, {@code false} altrimenti
     */
    public boolean contains(@NotNull UUID uuid) {
        return contains(uuid.toString());
    }

    /**
     * Metodo che permette di svuotare la lista ban.
     */
    public void clear(){
        users.clear();
    }
}
