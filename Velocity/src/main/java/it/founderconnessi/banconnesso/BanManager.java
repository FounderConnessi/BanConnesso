package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.AbsBanManager;
import it.founderconnessi.lib.BanUserFields;
import it.founderconnessi.lib.interfaces.PluginInt;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

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
                "plugins/banconnesso"
        );
    }

    public void checkOnlinePlayers() {
        BanConnesso.getInstance()
                .getServer()
                .getAllPlayers()
                .stream()
                .filter(player -> isBanned(
                        player.getUsername(),
                        player.getUniqueId())
                ).forEach(player -> {
                            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                                    player.getUsername(),
                                    player.getUniqueId()
                            );
                            player.disconnect(
                                    Config.getColoredMessage(
                                            user.replacePlaceholders(
                                                    BanConnesso.getInstance().getConfig().getString("kick-message")
                                            )
                                    )
                            );
                        }
                );
    }

    @Override
    public void refreshTask() {
        BanConnesso
                .getInstance()
                .getServer()
                .getScheduler()
                .buildTask(BanConnesso.getInstance(), this::loadBannedUsers)
                .repeat(plugin.getConfig().getInt("refresh-rate"), TimeUnit.MINUTES)
                .schedule();
    }
}
