package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.ConfigInt;
import net.md_5.bungee.api.ChatColor;

import java.util.List;

/**
 * Classe concreta che implementa l'intefaccia {@link ConfigInt}, utile per la gestione del config.
 */
public class Config implements ConfigInt {

    /**
     * Metodo che si occupa di colorare il messaggio in funzione dei codici colore presenti in esso.
     * @param path percorso.
      * @return messaggio colorato.
     */
    public static String getColoredMessage(String path) {
        return ChatColor.translateAlternateColorCodes(
                '&',
                BanConnesso.getInstance().getConfiguration().getString(path)
        );
    }

    @Override
    public boolean getBoolean(String path) {
        return BanConnesso.getInstance().getConfig().getBoolean(path);
    }

    @Override
    public String getString(String path) {
        return BanConnesso.getInstance().getConfig().getString(path);
    }

    @Override
    public int getInt(String path) {
        return BanConnesso.getInstance().getConfig().getInt(path);
    }

    @Override
    public List<String> getStringList(String path) {
        return BanConnesso.getInstance().getConfig().getStringList(path);
    }
}
