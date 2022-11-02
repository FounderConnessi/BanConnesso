package it.founderconnessi.banconnesso.listeners;

import it.founderconnessi.banconnesso.BanConnesso;
import it.founderconnessi.lib.BanUserFields;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class LoginListener implements Listener {

    @EventHandler
    public void PostLoginEvent(PostLoginEvent event){
        ProxiedPlayer player =  event.getPlayer();
        if(BanConnesso.getInstance().getBanManager().isBanned(player.getName(), player.getUniqueId())){
            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                    player.getName(),
                    player.getUniqueId()
            );
            player.disconnect(
                    new TextComponent(
                            user.replacePlaceholders(
                                    BanConnesso
                                            .getInstance()
                                            .getConfig()
                                            .getString("kick-message")
                            )
                    )
            );
        }
    }

}
