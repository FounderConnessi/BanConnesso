package it.founderconnessi.banconnesso;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import it.founderconnessi.banconnesso.commands.MainCommand;
import it.founderconnessi.banconnesso.files.CustomYaml;
import it.founderconnessi.banconnesso.listeners.LoginListener;
import it.founderconnessi.lib.utils.UpdateChecker;
import org.simpleyaml.configuration.file.YamlConfiguration;
import org.slf4j.Logger;

import java.nio.file.Path;

/**
 * Classe principale del plugin.
 */
@Plugin(
        id = "banconnesso",
        name = "BanConnesso",
        version = "1.0.0",
        url = "founderconnessi.it",
        authors = {"MrFreasy"}
)
public class BanConnesso {
    /**
     * Istanza del plugin.
     */
    private static BanConnesso instance;

    /**
     * Percorso della cartella del plugin.
     */
    public final Path dataDirectory;

    /**
     * Server proxy.
     */
    private final ProxyServer server;

    /**
     * Logger del server.
     */
    @Inject
    private final Logger logger;

    /**
     * Logger della libreria condivisa.
     */
    private final it.founderconnessi.banconnesso.Plugin plugin;

    /**
     * Gestore del ban.
     */
    private BanManager banManager;

    /**
     * Configurazione del plugin.
     */
    private CustomYaml config;

    @Inject
    public BanConnesso(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        instance = this;
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
        this.plugin = new it.founderconnessi.banconnesso.Plugin(
                new it.founderconnessi.banconnesso.Logger(),
                new Config()
        );
    }

    /**
     * Metodo che restituisce l'istanza del plugin.
     * @return istanza del plugin.
     */
    public static BanConnesso getInstance() {
        return instance;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        this.config = new CustomYaml(dataDirectory, "config");
        banManager = new BanManager(plugin);
        server.getEventManager().register(
                this,
                new LoginListener()
        );
        server.getCommandManager().register("banconnesso", new MainCommand(), "bc");
        if (config.getConfiguration().getBoolean("update-checker"))
            new UpdateChecker(plugin);
        logger.info("§aSviluppato da FounderConnessi.");
    }

    /**
     * Metodo da invocare per effettuare un reload del plugin.
     */
    public void reload() {
        config.reload();
        banManager.updateRequestBody();
        banManager.loadBannedUsers();
    }

    @Subscribe
    public void onDisable(ProxyShutdownEvent event) {
        logger.info("§4Sviluppato da FounderConnessi.");
    }

    /**
     * Metodo che restituisce il gestore del ban.
     * @return gestore del ban.
     */
    public BanManager getBanManager() {
        return banManager;
    }

    /**
     * Metodo che restituisce il server proxy
     * @return server proxy.
     */
    public ProxyServer getServer() {
        return server;
    }

    /**
     * Metodo che restituisce il logger del server.
     * @return logger del server.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Metodo che restituisce la configurazione del plugin.
     * @return configurazione del plugin.
     */
    public YamlConfiguration getConfig() {
        return config.getConfiguration();
    }
}
