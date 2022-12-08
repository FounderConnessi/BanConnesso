package it.founderconnessi.banconnesso.commands;

import it.founderconnessi.banconnesso.BanConnesso;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.jetbrains.annotations.NotNull;

/**
 * Classe concreta che implementa il comando principale del BanConnesso.
 */
public class MainCommand extends Command {

    public MainCommand(@NotNull String name, @NotNull String... aliases) {
        super(name, null, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer)
            return;

        if (args.length == 0) {
            sender.sendMessage(
                    new TextComponent(
                            "Comandi disponibili: \n                 " +
                            "/bc reload - Ricarica il plugin"
                    )
            );
        } else if (args[0].equalsIgnoreCase("reload")) {
            BanConnesso.getInstance().reload();
            sender.sendMessage(
                    new TextComponent("Plugin ricaricato con successo!")
            );
        }
    }
}
