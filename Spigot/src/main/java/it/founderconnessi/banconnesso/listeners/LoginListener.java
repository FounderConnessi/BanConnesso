package it.founderconnessi.banconnesso.listeners;

import it.founderconnessi.banconnesso.BanConnesso;
import it.founderconnessi.lib.BanUserFields;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class LoginListener implements Listener {

    @EventHandler
    public void PlayerLoginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (BanConnesso.getInstance().getBanManager().isBanned(player.getName(), player.getUniqueId())) {
            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                    player.getName(),
                    player.getUniqueId()
            );
            event.disallow(
                    PlayerLoginEvent.Result.KICK_BANNED,
                    user.replacePlaceholders(
                            BanConnesso
                                    .getInstance()
                                    .getConfig()
                                    .getString("kick-message")
                    )
            );
        }
    }

}
