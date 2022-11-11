package it.founderconnessi.banconnesso;

import it.founderconnessi.banconnesso.commands.MainCommand;
import it.founderconnessi.banconnesso.files.CustomYaml;
import it.founderconnessi.banconnesso.listeners.LoginListener;
import it.founderconnessi.banconnesso.utils.Bstats;
import it.founderconnessi.lib.utils.UpdateChecker;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

import java.nio.file.Paths;

/**
 * Classe principale del plugin.
 */
public final class BanConnesso extends Plugin {

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
        it.founderconnessi.banconnesso.Plugin plugin = new it.founderconnessi.banconnesso.Plugin(
                new Logger(),
                new Config()
        );
        banManager = new BanManager(plugin);
        getProxy().getPluginManager().registerListener(
                this,
                new LoginListener()
        );
        getProxy().getPluginManager().registerCommand(
                this,
                new MainCommand("banconnesso", "bc")
        );
        if (config.getConfiguration().getBoolean("update-checker"))
            new UpdateChecker(plugin);
        Bstats.sendMetrics();
        getProxy().getConsole().sendMessage(
                new TextComponent("§7[§cBanConnesso§7] §aSviluppato da FounderConnessi.")
        );
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
        getProxy().getConsole().sendMessage(
                new TextComponent("§7[§cBanConnesso§7] §4Sviluppato da FounderConnessi.")
        );
    }

    /**
     * Metodo che restituisce il gestore del ban.
     * @return gestore del ban.
     */
    public BanManager getBanManager() {
        return banManager;
    }

    /**
     * Metodo che restituisce la configurazione del plugin.
     * @return configurazione del plugin.
     */
    public Configuration getConfig() {
        return config.getConfiguration();
    }

}
