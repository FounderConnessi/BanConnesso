package it.founderconnessi.lib.utils;

import com.google.gson.JsonParser;
import it.founderconnessi.lib.PluginInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * Classe concreta immutabile che permette di controllare la presenza di nuovi aggiornamenti.<p>
 */
public class UpdateChecker {

    /**
     * Endpoint a cui effettuare le richiesta.
     */
    private final static String ENDPOINT = "https://api.founderconnessi.it/";

    /**
     * Versione corrente del plugin.
     */
    private final String currentVersion;

    /**
     * Ultima versione del plugin.
     */
    private final String latestVersion;

    /**
     * Logger.
     */
    private final PluginInt plugin;

    /**
     * Costruisce un oggetto di tipo {@link UpdateChecker}.
     * @param plugin plugin.
     */
    public UpdateChecker(PluginInt plugin) {
        this.plugin = plugin;
        this.currentVersion = plugin.getPluginVersion();
        this.latestVersion = getLastVersion();
        checkUpdate();
    }

    /**
     * Metodo che permette di ricevere l'ultima versione disponibile del plugin.
     * @return ultima versione disponibile.
     */
    private String getLastVersion() {
        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = br.readLine();
            return JsonParser.parseString(line).getAsJsonObject().get("version").getAsJsonObject().get(plugin.getServerType().toLowerCase()).getAsString();
        } catch (Exception ignored) {
            plugin.getLogger().warning("Controllo degli aggiornamenti fallito");
        }
        return null;
    }

    /**
     * Il metodo permette di controllare la presenza di un nuovo aggiornamento.
     * Notifica tramite messaggio in console solamente se si riscontra una nuova versione del plugin.
     */
    public void checkUpdate() {
        if (Objects.nonNull(latestVersion) && !latestVersion.equalsIgnoreCase(currentVersion)) {
            String space = "                ";
            plugin.sendConsoleMessage("\n\n" + space +
                    "§7§m____________________________________________________________________§r\n" + space +
                    "                            §r§cBanConnesso\n" + space +
                    "§7§m____________________________________________________________________§r\n" + space +
                    "§e                    Aggiornamento alla versione: " + latestVersion + "\n" + space +
                    "§e                      Scarica la nuova versione da:\n             " +
                    "§ehttps://github.com/FounderConnessi/BanConnesso/releases/tag/" + plugin.getServerType() + "-" + latestVersion + "\n" + space +
                    "§7§m____________________________________________________________________§r\n");
        }
    }

}
