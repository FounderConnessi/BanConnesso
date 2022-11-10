package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.LoggerInt;

/**
 * Classe concreta che implementa l'intefaccia {@link LoggerInt}, fornedo funzioni di log.
 */
public class Logger implements LoggerInt {

    @Override
    public void warning(String message) {
        BanConnesso.getInstance().getLogger().warning(message);
    }
}
