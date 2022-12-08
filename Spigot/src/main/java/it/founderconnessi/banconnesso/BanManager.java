package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.AbsBanManager;
import it.founderconnessi.lib.BanUserFields;
import it.founderconnessi.lib.interfaces.PluginInt;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
 * Classe concreta che estende la classe astratta {@link AbsBanManager}, implementando i metodi astratti.
 */
public class BanManager extends AbsBanManager {

    /**
     * Costruisce un oggetto di tipo {@link BanManager}
     * @param plugin plugin.
     */
    public BanManager(@NotNull PluginInt plugin) {
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
