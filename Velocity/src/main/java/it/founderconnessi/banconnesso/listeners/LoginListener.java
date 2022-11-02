package it.founderconnessi.banconnesso.listeners;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;
import it.founderconnessi.banconnesso.BanConnesso;
import it.founderconnessi.lib.BanUserFields;
import net.kyori.adventure.text.Component;

public class LoginListener {

    @Subscribe(order = PostOrder.FIRST)
    public void LoginEvent(LoginEvent event) {
        Player player = event.getPlayer();
        if (BanConnesso.getInstance().getBanManager().isBanned(player.getUsername(), player.getUniqueId())) {
            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                    player.getUsername(),
                    player.getUniqueId()
            );
            event.setResult(ResultedEvent.ComponentResult.denied(
                    Component.text(
                            user.replacePlaceholders(
                                    BanConnesso
                                            .getInstance()
                                            .getConfig()
                                            .getString("kick-message")
                            )
                    )
            ));
        }
    }

}
