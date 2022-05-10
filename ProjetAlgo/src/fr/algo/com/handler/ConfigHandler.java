package fr.algo.com.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.algo.com.utils.Config;

public class ConfigHandler {

    public static final Path configPath = Paths.get("./config.yml");

    private static ConfigHandler configHandler;

    private Config config;

    public static ConfigHandler getInstance() throws FileNotFoundException {
        return getInstance(configPath);
    }

    public static ConfigHandler getInstance(Path configPath) throws FileNotFoundException {
        if(configHandler == null) {
            configHandler = new ConfigHandler(configPath);
        }
        return configHandler;
    }

    private ConfigHandler(Path configPath) throws FileNotFoundException {
        this.config = loadConfig(configPath);       
    }

    public Config loadConfig(Path configPath) throws FileNotFoundException {
        Constructor constructor = new Constructor(Config.class);
        Yaml yaml = new Yaml(constructor);
        return yaml.load(new FileInputStream(configPath.toFile()));
    }

    public void dumpConfig() throws IllegalArgumentException, IllegalAccessException, IOException {
        dumpConfig(this.config, ConfigHandler.configPath);
    }

    public void dumpConfig(Config config, Path configPath) throws IllegalArgumentException, IllegalAccessException, IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(FlowStyle.BLOCK);
        options.setPrettyFlow(true);        
        Yaml yml = new Yaml(options);
        yml.dump(config, new FileWriter(configPath.toFile()));
    }

    public Config getConfig() {
        return this.config;
    }   

}