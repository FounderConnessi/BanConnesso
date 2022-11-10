package it.founderconnessi.lib;

/**
 * Interfaccia che permette di comunicare con il logger del server.
 */
public interface LoggerInt {

    /**
     * Metodo che permette di inviare un messaggio di warning.
     * @param message messaggio da inviare.
     */
    void warning(String message);
}
