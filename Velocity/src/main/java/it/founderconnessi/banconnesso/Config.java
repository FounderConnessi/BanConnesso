package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.ConfigInt;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;

/**
 * Classe concreta che implementa l'intefaccia {@link ConfigInt}, utile per la gestione del config.
 */
public class Config implements ConfigInt {

    /**
     * Serializzatore dei colori.
     */
    static LegacyComponentSerializer COLOR_SERIALIZER = LegacyComponentSerializer.builder().character('&').hexCharacter('#').hexColors().build();

    /**
     * Metodo che si occupa di colorare il messaggio in funzione dei codici colore presenti in esso.
     * @param message messaggio.
     * @return messaggio colorato.
     */
    public static TextComponent getColoredMessage(String message) {
        return COLOR_SERIALIZER.deserialize(message);
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
