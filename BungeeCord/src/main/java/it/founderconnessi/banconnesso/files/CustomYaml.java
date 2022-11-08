package it.founderconnessi.banconnesso.files;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


public class CustomYaml {

    private final String fileName;
    private final Path pluginPath;
    private Configuration configuration;

    public CustomYaml(Path pluginPath, String fileName) {
        this.fileName = fileName + ".yml";
        this.pluginPath = pluginPath;
        reload();
    }

    public void reload() {
        try {
            if (!Files.exists(pluginPath)) {
                Files.createDirectory(pluginPath);
            }
            Path configPath = pluginPath.resolve(fileName);
            if (!Files.exists(configPath)) {
                InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
                Files.copy(Objects.requireNonNull(in), configPath);
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}