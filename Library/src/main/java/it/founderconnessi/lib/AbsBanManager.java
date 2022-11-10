package it.founderconnessi.lib;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.founderconnessi.lib.api.Api;
import it.founderconnessi.lib.api.ApiFields;
import it.founderconnessi.lib.api.ApiFilters;
import it.founderconnessi.lib.api.ApiRequestBody;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * Classe astratta che rappresenta il gestore dei ban, che si occupa di gestire la lista degli utenti banditi.
 */
public abstract class AbsBanManager {

    /**
     * Lista degli utenti banditi.
     */
    protected final BanList banList;

    /**
     * Plugin.
     */
    protected final PluginInt plugin;

    /**
     * Percorso relativo della cartella del plugin.
     */
    protected final String pluginFolder;

    /**
     * Hashcode che permette di verificare velocemente se la lista <p>
     * degli utenti remota è più aggiornata rispetto a quella in memoria.
     */
    protected String hashCode;

    /**
     * Corpo della richiesta, necessario per le chiamate API.
     */
    protected ApiRequestBody requestBody;

    /**
     * Costruisce un gestore del ban.
     * @param plugin plugin.
     * @param pluginFolder percorso relativo della cartella del plugin.
     */
    public AbsBanManager(PluginInt plugin, String pluginFolder) {
        this.plugin = plugin;
        this.pluginFolder = pluginFolder;
        banList = new BanList();
        updateRequestBody();
        refreshTask();
    }

    /**
     * Metodo che carica la lista degli utenti bannati attraverso la chiamata API.
     */
    public void loadBannedUsers() {
        JsonObject rawData = Api.fetchUsers(requestBody, plugin.getLogger());
        if (Objects.isNull(rawData))
            rawData = loadJson();
        String newHashCode = rawData.get("hashCode").getAsString();
        if (Objects.nonNull(hashCode) && hashCode.equalsIgnoreCase(newHashCode))
            return;
        saveToJson(rawData);
        hashCode = newHashCode;
        banList.clear();
        JsonArray rawUsers = rawData.getAsJsonArray("users");
        String uniqueField = requestBody.getFields().isUuid() ? "uuid" : "nickname";
        for (JsonElement element : rawUsers) {
            JsonObject rawUser = element.getAsJsonObject();
            JsonElement startDate = rawUser.get("startDate");
            JsonElement reason = rawUser.get("reason");
            JsonElement gravity = rawUser.get("gravity");
            banList.addUser(
                    rawUser.get(uniqueField).getAsString(),
                    Objects.nonNull(startDate) ? startDate.getAsString() : null,
                    Objects.nonNull(reason) ? reason.getAsString() : null,
                    Objects.nonNull(gravity) ? Gravity.valueOf(gravity.getAsString()) : null
            );
        }
        checkOnlinePlayers();
    }

    /**
     * Metodo che permette di aggiornare il corpo della richiesta.
     * Si rivela utile quando viene modificato il file di configurazione.
     */
    public void updateRequestBody() {
        ApiFields fields = new ApiFields(
                plugin.getConfig().getBoolean("online-uuid"),
                !plugin.getConfig().getBoolean("online-uuid"),
                false,
                true,
                false
        );
        ApiFilters filters = new ApiFilters(
                plugin.getConfig()
                        .getStringList("gravities")
                        .stream()
                        .map(gravity -> Gravity.valueOf(gravity.toUpperCase()))
                        .toArray(Gravity[]::new)
        );
        requestBody = new ApiRequestBody(fields, filters);
    }

    /**
     * Metodo che permette di verificare se un utente risulta bandito.
     * @param nickname nickname del giocatore.
     * @param uuid uuid del giocatore.
     * @return {@code true} se l'utente è bandito, {@code false} altrimenti.
     */
    public boolean isBanned(String nickname, UUID uuid) {
        if (plugin.getConfig().getBoolean("online-uuid"))
            return banList.contains(uuid);
        else
            return banList.contains(nickname.toLowerCase());
    }

    /**
     * Metodo che permette di ottenere le informazioni di un utente bandito.
     * @param nickname nickname del giocatore.
     * @param uuid uuid del giocatore.
     * @return informazioni dell'utente bandito se presenti, {@code null} altrimenti.
     */
    public BanUserFields getUser(String nickname, UUID uuid) {
        if (plugin.getConfig().getBoolean("online-uuid"))
            return banList.getUser(uuid.toString());
        else
            return banList.getUser(nickname.toLowerCase());
    }

    /**
     * Metodo che permette di salvare su file un oggetto in formato JSON.
     * Si rivela utile per salvare la lista degli utenti banditi su file.
     * @param jsonObject oggetto json.
     */
    private void saveToJson(JsonObject jsonObject) {
        FileWriter file;
        try {
            file = new FileWriter(pluginFolder + "/save.json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che permette di caricare da file un oggetto in formato JSON.
     * Si rivela utile quando la richiesta API fallisce.
     * @return oggetto json.
     */
    private JsonObject loadJson() {
        try {
            return (JsonObject) JsonParser.parseReader(
                    new FileReader(pluginFolder + "/save.json")
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo che controlla gli utenti online e caccia fuori dal server gli utenti banditi.
     */
    public abstract void checkOnlinePlayers();

    /**
     * Metodo che genera un task che si ripete, con frequenza impostabile sul config,
     * per invocare il metodo {@link #loadBannedUsers()} che ricarica la lista degli utenti banditi.
     */
    protected abstract void refreshTask();
}
