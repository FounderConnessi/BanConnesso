package it.founderconnessi.banconnesso;

import it.founderconnessi.banconnesso.commands.MainCommand;
import it.founderconnessi.banconnesso.files.CustomYaml;
import it.founderconnessi.banconnesso.listeners.LoginListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public final class BanConnesso extends JavaPlugin {

    private static BanConnesso instance;
    private BanManager banManager;
    private CustomYaml config;

    public static BanConnesso getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        config = new CustomYaml("config");
        banManager = new BanManager();
        getServer().getPluginManager().registerEvents(
                new LoginListener(),
                this
        );
        getCommand("banconnesso").setExecutor(new MainCommand());
        Bukkit.getConsoleSender().sendMessage("§8§l[§c§lBanConnesso§8§l] §aSviluppato da FounderConnessi.");
    }

    public void reload() {
        config.reload();
        banManager.updateRequestBody();
        banManager.loadBannedUsers();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§8§l[§c§lBanConnesso§8§l] §4Sviluppato da FounderConnessi.");
    }

    public BanManager getBanManager() {
        return banManager;
    }

    public Configuration getConfiguration() {
        return config.getConfiguration();
    }

}
