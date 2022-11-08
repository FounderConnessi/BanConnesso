package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.PluginInt;

public class Plugin implements PluginInt {
    @Override
    public String getPluginVersion() {
        return BanConnesso.getInstance().getDescription().getVersion();
    }

    @Override
    public String getServerType() {
        return "Spigot";
    }

    @Override
    public void sendConsoleMessage(String message) {
        BanConnesso.getInstance().getServer().getConsoleSender().sendMessage(message);
    }
}
