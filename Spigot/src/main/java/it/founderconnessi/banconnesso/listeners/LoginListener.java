package it.founderconnessi.banconnesso.listeners;

import it.founderconnessi.banconnesso.BanConnesso;
import it.founderconnessi.banconnesso.Config;
import it.founderconnessi.lib.BanUserFields;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

/**
 * Classe concreta che implementa l'interfaccia {@link Listener}.
 * Si occupa di gestire l'evento specifico di login di un utente all'interno del server
 * per verificare la presenza del ban, espellendolo dal server in caso positivo.
 */
public class LoginListener implements Listener {

    /**
     * Metodo che verifica durare il login se l'utente è bandito, espellendolo in caso positivo.
     */
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
                            Config.getColoredMessage("kick-message")
                    )
            );
        }
    }

}
