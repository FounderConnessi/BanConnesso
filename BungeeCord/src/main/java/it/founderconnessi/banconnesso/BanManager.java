package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.AbsBanManager;
import it.founderconnessi.lib.BanUserFields;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.concurrent.TimeUnit;

public class BanManager extends AbsBanManager {

    public BanManager() {
        super(
                new Logger(),
                new Config(),
                "plugins/BanConnesso"
        );
    }

    @Override
    public void checkOnlinePlayers() {
        BanConnesso.getInstance()
                .getProxy()
                .getPlayers()
                .stream()
                .filter(player -> isBanned(
                        player.getName(),
                        player.getUniqueId())
                ).forEach(player -> {
                            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                                    player.getName(),
                                    player.getUniqueId()
                            );
                            player.disconnect(
                                    new TextComponent(
                                            user.replacePlaceholders(
                                                    Config.getColoredMessage("kick-message")
                                            )
                                    )
                            );
                        }
                );
    }

    @Override
    public void refreshTask() {
        BanConnesso.getInstance().getProxy().getScheduler().schedule(
                BanConnesso.getInstance(),
                this::loadBannedUsers,
                1,
                config.getInt("refresh-rate") * 60L,
                TimeUnit.SECONDS
        );
    }
}
