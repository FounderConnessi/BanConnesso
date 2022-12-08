package it.founderconnessi.lib.interfaces;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Interfaccia che descrive il contratto di un file di configurazione.
 */
public interface ConfigInt {

    /**
     * Metodo che permette di ottenere il valore di tipo booleano dato il percorso.
     * @param path percorso.
     * @return {@code true} se il valore corrisponde a vero, {@code false} altrimenti.
     */
    boolean getBoolean(@NotNull String path);

    /**
     * Metodo che permette di ottenere il valore di tipo stringa dato il percorso.
     * @param path percorso.
     * @return stringa corrispondente.
     */
    @Nullable
    String getString(@NotNull String path);

    /**
     * Metodo che permette di ottenere il valore di tipo intero dato il percorso.
     * @param path percorso.
     * @return intero corrispondente.
     */
    int getInt(@NotNull String path);

    /**
     * Metodo che permette di ottenere il valore di tipo lista di stringhe dato il percorso.
     * @param path percorso.
     * @return lista di stringhe corrispondente.
     */
    @Nullable
    List<String> getStringList(@NotNull String path);
}
