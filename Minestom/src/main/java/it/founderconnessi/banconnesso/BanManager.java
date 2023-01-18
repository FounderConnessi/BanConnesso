package it.founderconnessi.banconnesso;

import it.founderconnessi.lib.AbsBanManager;
import it.founderconnessi.lib.BanUserFields;
import it.founderconnessi.lib.interfaces.PluginInt;
import net.minestom.server.MinecraftServer;
import net.minestom.server.timer.TaskSchedule;
import org.jetbrains.annotations.NotNull;

public class BanManager extends AbsBanManager {

    public BanManager(@NotNull PluginInt plugin) {
        super(plugin, "extensions/BanConnesso");
    }

    @Override
    public void checkOnlinePlayers() {
        MinecraftServer.getConnectionManager()
                .getOnlinePlayers()
                .stream()
                .filter(player -> isBanned(
                        player.getUsername(),
                        player.getUuid())
                ).forEach(player -> {
                            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                                    player.getUsername(),
                                    player.getUuid()
                            );
                            player.kick(
                                    user.replacePlaceholders(
                                            Config.getColoredMessage("kick-message")
                                    )
                            );
                        }
                );
    }

    @Override
    public void refreshTask() {
        MinecraftServer.getSchedulerManager().scheduleTask(
                this::loadBannedUsers,
                TaskSchedule.tick(1),
                TaskSchedule.minutes(plugin.getConfig().getInt("refresh-rate"))
        );
    }
}
