package it.founderconnessi.banconnesso.listeners;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;
import it.founderconnessi.banconnesso.BanConnesso;
import it.founderconnessi.banconnesso.Config;
import it.founderconnessi.lib.BanUserFields;

/**
 * Classe concreta che si occupa di gestire l'evento specifico di login di un utente all'interno del server
 * per verificare la presenza del ban, espellendolo dal server in caso positivo.
 */
public class LoginListener {

    /**
     * Metodo che verifica durare il login se l'utente è bandito, espellendolo in caso positivo.
     */
    @Subscribe(order = PostOrder.FIRST)
    public void LoginEvent(LoginEvent event) {
        Player player = event.getPlayer();
        if (BanConnesso.getInstance().getBanManager().isBanned(player.getUsername(), player.getUniqueId())) {
            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                    player.getUsername(),
                    player.getUniqueId()
            );
            event.setResult(ResultedEvent.ComponentResult.denied(
                    Config.getColoredMessage(
                            user.replacePlaceholders(
                                    BanConnesso.getInstance().getConfig().getString("kick-message")
                            )
                    )
            ));
        }
    }

}
