package it.founderconnessi.lib.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * Interfaccia che fornisce alcuni metodi per comunicare con lo stato interno del Plugin.
 */
public interface PluginInt {

    /**
     * Metodo che permette di ottenere la versione del plugin.
     * @return versione del plugin.
     */
    @NotNull
    String getPluginVersion();

    /**
     * Metodo che permette di conoscere il tipo di server.
     * Ovvero BungeeCord, Velocity o Spigot.
     * @return tipo di server.
     */
    @NotNull
    String getServerType();

    /**
     * Metodo che permette di inviare un messaggio in console.
     * @param message testo da inviare.
     */
    void sendConsoleMessage(@NotNull String message);

    /**
     * Metodo che restituisce il Logger.
     * @return logger.
     */
    @NotNull
    LoggerInt getLogger();

    /**
     * Metodo che restituisce il Config.
     * @return config.
     */
    @NotNull
    ConfigInt getConfig();
}
