package it.founderconnessi.lib;

import java.util.List;

/**
 * Interfaccia che descrive il contratto un file di configurazione.
 */
public interface ConfigInt {

    /**
     * Metodo che permette di ottenere il valore di tipo booleano dato il percorso.
     * @param path percorso.
     * @return {@code true} se il valore corrisponde a vero, {@code false} altrimenti.
     */
    boolean getBoolean(String path);

    /**
     * Metodo che permette di ottenere il valore di tipo stringa dato il percorso.
     * @param path percorso.
     * @return stringa corrispondente.
     */
    String getString(String path);

    /**
     * Metodo che permette di ottenere il valore di tipo intero dato il percorso.
     * @param path percorso.
     * @return intero corrispondente.
     */
    int getInt(String path);

    /**
     * Metodo che permette di ottenere il valore di tipo lista di stringhe dato il percorso.
     * @param path percorso.
     * @return lista di stringhe corrispondente.
     */
    List<String> getStringList(String path);
}
