package it.founderconnessi.lib.utils;

import com.google.gson.JsonParser;
import it.founderconnessi.lib.PluginInt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class UpdateChecker {

    private final static String ENDPOINT = "http://localhost:3000/";
    private final String currentVersion;
    private final PluginInt plugin;
    private final String latestVersion;

    public UpdateChecker(PluginInt plugin) {
        this.plugin = plugin;
        this.currentVersion = plugin.getPluginVersion();
        this.latestVersion = getLastVersion();
        checkUpdate();
    }

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
