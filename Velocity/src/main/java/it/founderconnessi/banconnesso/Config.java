package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.ConfigInt;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;

public class Config implements ConfigInt {

    static LegacyComponentSerializer COLOR_SERIALIZER = LegacyComponentSerializer.builder().character('&').hexCharacter('#').hexColors().build();

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
