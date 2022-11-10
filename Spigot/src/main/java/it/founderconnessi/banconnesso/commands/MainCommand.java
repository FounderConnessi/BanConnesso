package it.founderconnessi.banconnesso.commands;

import it.founderconnessi.banconnesso.BanConnesso;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/**
 * Classe concreta che implementa il comando principale del BanConnesso.
 */
public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender))
            return true;

        if (args.length == 0) {
            sender.sendMessage(
                    "Comandi disponibili: \n                 " +
                    "/bc reload - Ricarica il plugin"
            );
        } else if (args[0].equalsIgnoreCase("reload")) {
            BanConnesso.getInstance().reload();
            sender.sendMessage("Plugin ricaricato con successo!");
        }
        return true;
    }
}
