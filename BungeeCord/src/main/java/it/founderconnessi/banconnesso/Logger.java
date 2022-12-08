package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.interfaces.LoggerInt;
import org.jetbrains.annotations.NotNull;

/**
 * Classe concreta che implementa l'intefaccia {@link LoggerInt}, fornedo funzioni di log.
 */
public class Logger implements LoggerInt {
    
    @Override
    public void warning(@NotNull String message) {
        BanConnesso.getInstance().getLogger().warning(message);
    }
}
