package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.ConfigInt;
import net.md_5.bungee.api.ChatColor;

import java.util.List;

public class Config implements ConfigInt {

    public static String getColoredMessage(String path) {
        return ChatColor.translateAlternateColorCodes(
                '&',
                BanConnesso.getInstance().getConfig().getString(path)
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
