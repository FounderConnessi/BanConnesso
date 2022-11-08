package it.founderconnessi.banconnesso.files;

import com.google.common.io.ByteStreams;
import it.founderconnessi.banconnesso.BanConnesso;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class CustomYaml {

    private final String fileName;
    private YamlConfiguration configuration;

    public CustomYaml(String fileName) {
        this.fileName = fileName;
        reload();
    }

    public void reload() {
        BanConnesso.getInstance().getDataFolder().mkdirs();
        File file = new File(BanConnesso.getInstance().getDataFolder(), fileName + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                InputStream is = BanConnesso.getInstance().getClass().getClassLoader().getResourceAsStream(fileName + ".yml");
                OutputStream os = Files.newOutputStream(file.toPath());
                ByteStreams.copy(is, os);
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.configuration = YamlConfiguration.loadConfiguration(file);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}