package it.founderconnessi.banconnesso;

import it.founderconnessi.banconnesso.commands.MainCommand;
import it.founderconnessi.banconnesso.files.CustomYaml;
import it.founderconnessi.banconnesso.listeners.LoginListener;
import it.founderconnessi.banconnesso.utils.Bstats;
import it.founderconnessi.lib.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Paths;

/**
 * Classe principale del plugin.
 */
public final class BanConnesso extends JavaPlugin {

    /**
     * Istanza del plugin.
     */
    private static BanConnesso instance;

    /**
     * Gestore del ban.
     */
    private BanManager banManager;

    /**
     * Configurazione del plugin.
     */
    private CustomYaml config;

    /**
     * Metodo che restituisce l'istanza del plugin.
     * @return istanza del plugin.
     */
    @NotNull
    public static BanConnesso getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        config = new CustomYaml(
                Paths.get("plugins/BanConnesso"),
                "config"
        );
        Plugin plugin = new Plugin(
                new Logger(),
                new Config()
        );
        banManager = new BanManager(plugin);
        getServer().getPluginManager().registerEvents(
                new LoginListener(),
                this
        );
        getCommand("banconnesso").setExecutor(new MainCommand());
        if (config.getConfiguration().getBoolean("update-checker"))
            new UpdateChecker(plugin);
        Bstats.sendMetrics();
        Bukkit.getConsoleSender().sendMessage("§8§l[§c§lBanConnesso§8§l] §aSviluppato da FounderConnessi.");
    }

    /**
     * Metodo da invocare per effettuare un reload del plugin.
     */
    public void reload() {
        config.reload();
        banManager.updateRequestBody();
        banManager.loadBannedUsers();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8§l[§c§lBanConnesso§8§l] §4Sviluppato da FounderConnessi.");
    }

    /**
     * Metodo che restituisce il gestore del ban.
     * @return gestore del ban.
     */
    @NotNull
    public BanManager getBanManager() {
        return banManager;
    }

    /**
     * Metodo che restituisce la configurazione del plugin.
     * @return configurazione del plugin.
     */
    @NotNull
    public Configuration getConfiguration() {
        return config.getConfiguration();
    }

}
