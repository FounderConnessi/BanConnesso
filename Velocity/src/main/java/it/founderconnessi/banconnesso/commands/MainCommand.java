package it.founderconnessi.banconnesso.commands;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import it.founderconnessi.banconnesso.BanConnesso;
import net.kyori.adventure.text.Component;

public class MainCommand implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        CommandSource commandSource = invocation.source();
        if (commandSource instanceof Player)
            return;

        String[] args = invocation.arguments();
        if (args.length == 0) {
            commandSource.sendMessage(
                    Component.text(
                            "Comandi disponibili: \n                 " +
                                    "/bc reload - Ricarica il plugin"
                    )
            );
        } else if (args[0].equalsIgnoreCase("reload")) {
            BanConnesso.getInstance().reload();
            commandSource.sendMessage(
                    Component.text("Plugin ricaricato con successo!")
            );
        }
    }
}
