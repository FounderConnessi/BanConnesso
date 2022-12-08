package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.interfaces.ConfigInt;
import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
    @NotNull
    public static String getColoredMessage(@NotNull String path) {
        return ChatColor.translateAlternateColorCodes(
                '&',
                BanConnesso.getInstance().getConfiguration().getString(path)
        );
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        return BanConnesso.getInstance().getConfig().getBoolean(path);
    }

    @Override
    @Nullable
    public String getString(@NotNull String path) {
        return BanConnesso.getInstance().getConfig().getString(path);
    }

    @Override
    public int getInt(@NotNull String path) {
        return BanConnesso.getInstance().getConfig().getInt(path);
    }

    @Override
    @Nullable
    public List<String> getStringList(@NotNull String path) {
        return BanConnesso.getInstance().getConfig().getStringList(path);
    }
}
