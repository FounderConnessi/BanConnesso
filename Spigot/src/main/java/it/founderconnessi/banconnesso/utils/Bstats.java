package it.founderconnessi.banconnesso.utils;

import it.founderconnessi.banconnesso.BanConnesso;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;

/**
 * Classe concreta utile per l'invio delle metriche del server, che saranno gestite da bStats.
 * @see <a href="https://bstats.org">bStats</a>
 */
public class Bstats {

    /**
     * Metodo che invia le metriche del server a bStats.
     */
    public static void sendMetrics() {
        int pluginId = 16832;
        Metrics metrics = new Metrics(BanConnesso.getInstance(), pluginId);
        metrics.addCustomChart(
                new SimplePie(
                        "server_names",
                        () -> BanConnesso.getInstance().getConfig().getString("server-name")
                )
        );
    }
}
