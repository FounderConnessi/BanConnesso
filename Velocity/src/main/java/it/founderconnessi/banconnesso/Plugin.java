package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.PluginInt;
import net.kyori.adventure.text.Component;

public class Plugin implements PluginInt {
    @Override
    public String getPluginVersion() {
        return String.valueOf(
                BanConnesso.getInstance().getServer().getPluginManager().getPlugin("banconnesso").get().getDescription().getVersion()
        );
    }

    @Override
    public String getServerType() {
        return "Spigot";
    }

    @Override
    public void sendConsoleMessage(String message) {
        BanConnesso.getInstance().getServer().sendMessage(
                Component.text(message)
        );
    }
}
