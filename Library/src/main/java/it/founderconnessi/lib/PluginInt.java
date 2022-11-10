package it.founderconnessi.lib;

/**
 * Interfaccia che fornisce alcuni metodi per comunicare con lo stato interno del Plugin.
 */
public interface PluginInt {

    /**
     * Metodo che permette di ottenere la versione del plugin.
     * @return versione del plugin.
     */
    String getPluginVersion();

    /**
     * Metodo che permette di conoscere il tipo di server.
     * Ovvero BungeeCord, Velocity o Spigot.
     * @return tipo di server.
     */
    String getServerType();

    /**
     * Metodo che permette di inviare un messaggio in console.
     * @param message testo da inviare.
     */
    void sendConsoleMessage(String message);

    /**
     * Metodo che restituisce il Logger.
     * @return logger.
     */
    LoggerInt getLogger();

    /**
     * Metodo che restituisce il Config.
     * @return config.
     */
    ConfigInt getConfig();
}
