package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.AbsBanManager;
import it.founderconnessi.lib.BanUserFields;
import it.founderconnessi.lib.PluginInt;
import org.bukkit.Bukkit;

public class BanManager extends AbsBanManager {

    public BanManager(PluginInt plugin) {
        super(
                plugin,
                "plugins/BanConnesso"
        );
    }

    @Override
    public void checkOnlinePlayers() {
        BanConnesso.getInstance()
                .getServer()
                .getOnlinePlayers()
                .stream()
                .filter(player -> isBanned(
                        player.getName(),
                        player.getUniqueId())
                ).forEach(player -> {
                            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                                    player.getName(),
                                    player.getUniqueId()
                            );
                            player.kickPlayer(
                                    user.replacePlaceholders(
                                            Config.getColoredMessage("kick-message")
                                    )
                            );
                        }
                );
    }

    @Override
    public void refreshTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(
                BanConnesso.getInstance(),
                this::loadBannedUsers,
                1,
                plugin.getConfig().getInt("refresh-rate") * 1200L
        );
    }
}
