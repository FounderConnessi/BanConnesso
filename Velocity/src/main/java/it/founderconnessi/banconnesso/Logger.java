package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.LoggerInt;

public class Logger implements LoggerInt {

    @Override
    public void warning(String message) {
        BanConnesso.getInstance().getLogger().warn(message);
    }
}
