package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.PluginInt;
import net.md_5.bungee.api.chat.TextComponent;

public class Plugin implements PluginInt {
    @Override
    public String getPluginVersion() {
        return BanConnesso.getInstance().getDescription().getVersion();
    }

    @Override
    public String getServerType() {
        return "BungeeCord";
    }

    @Override
    public void sendConsoleMessage(String message) {
        BanConnesso.getInstance().getProxy().getConsole().sendMessage(
                new TextComponent(message)
        );
    }
}
