package it.founderconnessi.banconnesso;

import it.founderconnessi.banconnesso.commands.MainCommand;
import it.founderconnessi.banconnesso.files.CustomYaml;
import it.founderconnessi.lib.BanUserFields;
import it.founderconnessi.lib.utils.UpdateChecker;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.extensions.Extension;
import org.jetbrains.annotations.NotNull;
import org.simpleyaml.configuration.Configuration;

import java.nio.file.Paths;

public class BanConnesso extends Extension {

    private static BanConnesso instance;
    private BanManager banManager;
    private CustomYaml config;

    @NotNull
    public static BanConnesso getInstance() {
        return instance;
    }


    public void reload() {
        config.reload();
        banManager.updateRequestBody();
        banManager.loadBannedUsers();
    }


    @NotNull
    public BanManager getBanManager() {
        return banManager;
    }

    @NotNull
    public Configuration getConfiguration() {
        return config.getConfiguration();
    }

    @Override
    public void initialize() {
        instance = this;

        Plugin plugin = new Plugin(new Logger(), new Config());

        config = new CustomYaml(Paths.get("extensions/BanConnesso"), "config");
        banManager = new BanManager(plugin);

        MinecraftServer.getGlobalEventHandler().addListener(PlayerLoginEvent.class, event -> {
            Player player = event.getPlayer();
            if (BanConnesso.getInstance().getBanManager().isBanned(player.getUsername(), player.getUuid())) {
                BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                        player.getUsername(),
                        player.getUuid()
                );
                player.kick(user.replacePlaceholders(Config.getColoredMessage("kick-message")));
            }
        });

        MinecraftServer.getCommandManager().register(new MainCommand("banconnesso", "bc"));

        if (config.getConfiguration().getBoolean("update-checker"))
            new UpdateChecker(plugin);

        MinecraftServer.getCommandManager().getConsoleSender().sendMessage(
                "§8§l[§c§lBanConnesso§8§l] §aSviluppato da FounderConnessi."
        );
    }

    @Override
    public void terminate() {
        MinecraftServer.getCommandManager().getConsoleSender().sendMessage(
                "§8§l[§c§lBanConnesso§8§l] §4Sviluppato da FounderConnessi."
        );
    }
}
