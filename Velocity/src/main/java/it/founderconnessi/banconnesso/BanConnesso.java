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
import org.simpleyaml.configuration.file.YamlConfiguration;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        id = "banconnesso",
        name = "BanConnesso",
        version = "1.0.0",
        url = "founderconnessi.it",
        authors = {"MrFreasy"}
)
public class BanConnesso {
    private static BanConnesso instance;
    public final Path dataDirectory;
    private final ProxyServer server;
    @Inject
    private final Logger logger;
    private BanManager banManager;

    private final CustomYaml config;

    @Inject
    public BanConnesso(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        instance = this;
        this.server = server;
        this.logger = logger;
        this.dataDirectory = dataDirectory;
        this.config = new CustomYaml(dataDirectory, "config");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        this.banManager = new BanManager();
        server.getEventManager().register(
                this,
                new LoginListener()
        );
        server.getCommandManager().register("banconnesso", new MainCommand(), "bc");
        logger.info("§aSviluppato da FounderConnessi.");
    }

    public void reload() {
        config.reload();
        banManager.updateRequestBody();
        banManager.loadBannedUsers();
    }

    @Subscribe
    public void onDisable(ProxyShutdownEvent event) {
        logger.info("§4Sviluppato da FounderConnessi.");
    }

    public static BanConnesso getInstance() {
        return instance;
    }

    public BanManager getBanManager() {
        return banManager;
    }

    public ProxyServer getServer() {
        return server;
    }

    public Logger getLogger() {
        return logger;
    }

    public YamlConfiguration getConfig() {
        return config.getConfiguration();
    }

}
