package it.founderconnessi.banconnesso.listeners;

import it.founderconnessi.banconnesso.BanConnesso;
import it.founderconnessi.banconnesso.Config;
import it.founderconnessi.lib.BanUserFields;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

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
    public void PostLoginEvent(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (BanConnesso.getInstance().getBanManager().isBanned(player.getName(), player.getUniqueId())) {
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
    }

}
