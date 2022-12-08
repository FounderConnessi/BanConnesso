package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.interfaces.ConfigInt;
import it.founderconnessi.lib.interfaces.LoggerInt;
import it.founderconnessi.lib.interfaces.PluginInt;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Classe concreta che implementa l'intefaccia {@link PluginInt}.
 */
public class Plugin implements PluginInt {

    /**
     * Logger.
     */
    private final LoggerInt logger;

    /**
     * Config.
     */
    private final ConfigInt config;

    /**
     * Costruisce un oggetto di tipo {@link Plugin}.
     * @param logger logger.
     * @param config config.
     */
    public Plugin(@NotNull LoggerInt logger, @NotNull ConfigInt config) {
        this.logger = logger;
        this.config = config;
    }

    @Override
    @NotNull
    public String getPluginVersion() {
        return BanConnesso.getInstance().getServer().getPluginManager().getPlugin("banconnesso").get().getDescription().getVersion().get();
    }

    @Override
    @NotNull
    public String getServerType() {
        return "Velocity";
    }

    @Override
    public void sendConsoleMessage(@NotNull String message) {
        BanConnesso.getInstance().getServer().sendMessage(
                Component.text(message)
        );
    }

    @Override
    @NotNull
    public LoggerInt getLogger() {
        return logger;
    }

    @Override
    @NotNull
    public ConfigInt getConfig() {
        return config;
    }
}
