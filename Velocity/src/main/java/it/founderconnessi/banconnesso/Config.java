package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.interfaces.ConfigInt;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Classe concreta che implementa l'intefaccia {@link ConfigInt}, utile per la gestione del config.
 */
public class Config implements ConfigInt {

    /**
     * Serializzatore dei colori.
     */
    static final LegacyComponentSerializer COLOR_SERIALIZER = LegacyComponentSerializer.builder().character('&').hexCharacter('#').hexColors().build();

    /**
     * Metodo che si occupa di colorare il messaggio in funzione dei codici colore presenti in esso.
     * @param message messaggio.
     * @return messaggio colorato.
     */
    @NotNull
    public static TextComponent getColoredMessage(@NotNull String message) {
        return COLOR_SERIALIZER.deserialize(message);
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
