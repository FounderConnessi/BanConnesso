package it.founderconnessi.banconnesso.files;

import com.google.common.io.ByteStreams;
import it.founderconnessi.banconnesso.BanConnesso;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class CustomYaml {

    private final String fileName;
    private Configuration configuration;

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

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}