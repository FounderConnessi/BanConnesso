package it.founderconnessi.banconnesso.files;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;


public class CustomYaml {

    private final String fileName;
    private final Path pluginPath;
    private YamlConfiguration configuration;

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
            configuration = YamlConfiguration.loadConfiguration(configPath.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }
}