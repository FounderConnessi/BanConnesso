package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.ConfigInt;
import it.founderconnessi.lib.LoggerInt;
import it.founderconnessi.lib.PluginInt;
import net.md_5.bungee.api.chat.TextComponent;

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
        return "BungeeCord";
    }

    @Override
    public void sendConsoleMessage(String message) {
        BanConnesso.getInstance().getProxy().getConsole().sendMessage(
                new TextComponent(message)
        );
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
