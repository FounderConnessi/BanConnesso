package it.founderconnessi.banconnesso.files;

import com.google.common.io.ByteStreams;
import it.founderconnessi.banconnesso.BanConnesso;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;


public class CustomYaml {

    private final String fileName;
    private final Path path;
    private YamlConfiguration configuration;

    public CustomYaml(Path path, String fileName) {
        this.fileName = fileName + ".yml";
        this.path = path;
        reload();
    }

    public void reload() {

        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        File file = new File(path.toString(), fileName);
        if (!file.exists()) {
            try {
                InputStream is = BanConnesso.getInstance().getClass().getClassLoader().getResourceAsStream(fileName + ".yml");
                OutputStream os = Files.newOutputStream(file.toPath());
                ByteStreams.copy(is, os);
                is.close();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            configuration = YamlConfiguration.loadConfiguration(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }
}