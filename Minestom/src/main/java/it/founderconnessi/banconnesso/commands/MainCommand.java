package it.founderconnessi.banconnesso.commands;

import it.founderconnessi.banconnesso.BanConnesso;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.arguments.ArgumentType;
import org.jetbrains.annotations.NotNull;

public class MainCommand extends Command {

    public MainCommand(@NotNull String name, String... aliases) {
        super(name, aliases);

        setCondition((sender, commandString) -> sender instanceof ConsoleSender);

        setDefaultExecutor((sender, context) -> sender.sendMessage(
                "Comandi disponibili: \n                 " +
                        "/bc reload - Ricarica il plugin"
        ));

        addSyntax((sender, context) -> {
            BanConnesso.getInstance().reload();
            sender.sendMessage("Plugin ricaricato con successo!");
        }, ArgumentType.Literal("reload"));
    }

}
