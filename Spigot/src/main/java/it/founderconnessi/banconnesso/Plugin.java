package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.ConfigInt;
import it.founderconnessi.lib.LoggerInt;
import it.founderconnessi.lib.PluginInt;

public class Plugin implements PluginInt {

    private final LoggerInt logger;
    private final ConfigInt config;

    public Plugin(LoggerInt logger, ConfigInt config) {
        this.logger = logger;
        this.config = config;
    }

    @Override
    public String getPluginVersion() {
        return BanConnesso.getInstance().getDescription().getVersion();
    }

    @Override
    public String getServerType() {
        return "Spigot";
    }

    @Override
    public void sendConsoleMessage(String message) {
        BanConnesso.getInstance().getServer().getConsoleSender().sendMessage(message);
    }

    @Override
    public LoggerInt getLogger() {
        return logger;
    }

    @Override
    public ConfigInt getConfig() {
        return config;
    }
}
