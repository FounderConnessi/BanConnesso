package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.AbsBanManager;
import it.founderconnessi.lib.BanUserFields;

import java.util.concurrent.TimeUnit;

public class BanManager extends AbsBanManager {

    public BanManager() {
        super(
                new Logger(),
                new Config(),
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
                .repeat(config.getInt("refresh-rate"), TimeUnit.MINUTES)
                .schedule();
    }
}
