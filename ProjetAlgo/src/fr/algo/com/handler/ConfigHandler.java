package fr.algo.com.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import fr.algo.com.utils.Config;

/**
 * <b>Classe Handler de Config </b>
 * <p>
 *   Cette classe va réaliser les méthodes liées aux fichiers de configurations
 *   Cette classe réalise les traitements en arrière plan
 * 
 * @author Thomas, Benjamin
 * @version 1.0
 */
public class ConfigHandler {

	/**
     * Chemin vers le fichier yaml
     */
    public static final Path configPath = Paths.get("./config.yml");

    /**
     * Instance de la classe
     */
    private static ConfigHandler configHandler;

    /**
     * Instance de l'objet config
     * @see Config
     */
    private Config config;

    
    /**
     * Retourne une instance de cette classe
     * @throws FileNotFoundException si un fichier est introuvable
     * @return une instance de ConfigHandler
     */
    public static ConfigHandler getInstance() throws FileNotFoundException {
        return getInstance(configPath);
    }

    /**
     * Retourne une instance de cette classe
     * @throws FileNotFoundException si un fichier est introuvable
     * @return une instance de ConfigHandler
     * @param configPath chemin vers le fichier
     */
    public static ConfigHandler getInstance(Path configPath) throws FileNotFoundException {
        if(configHandler == null) {
            configHandler = new ConfigHandler(configPath);
        }
        return configHandler;
    }

    /**
 	* Constructeur de la classe
 	* @param configPath chemin vers le fichier
 	*/
    private ConfigHandler(Path configPath) throws FileNotFoundException {
        this.config = loadConfig(configPath);       
    }

    /**
     * Méthode pour charger un fichier
     * @throws FileNotFoundException si un fichier est introuvable
     * @return une instance de Config
     * @param configPath chemin vers le fichier
     * @see Config
     */
    public Config loadConfig(Path configPath) throws FileNotFoundException {
        Constructor constructor = new Constructor(Config.class);
        Yaml yaml = new Yaml(constructor);
        return yaml.load(new FileInputStream(configPath.toFile()));
    }

    /**
     * Méthode pour écrire dans un fichier yaml
     * @throws IllegalArgumentException si un argument est interdit
     * @throws IllegalAccessException si l'accès est interdit
     * @throws IOException si les données sont incorrectes 
     */
    public void dumpConfig() throws IllegalArgumentException, IllegalAccessException, IOException {
        dumpConfig(this.config, ConfigHandler.configPath);
    }

    /**
     * Méthode pour écrire dans un fichier yaml
     * @throws IllegalArgumentException si un argument est interdit
     * @throws IllegalAccessException si l'accès est interdit
     * @throws IOException si les données sont incorrectes 
     * @param config une instance de fichier de configuration
     * @param configPath chemin vers le fichier
     * @see Config
     */
    public void dumpConfig(Config config, Path configPath) throws IllegalArgumentException, IllegalAccessException, IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(FlowStyle.BLOCK);
        options.setPrettyFlow(true);        
        Yaml yml = new Yaml(options);
        yml.dump(config, new FileWriter(configPath.toFile()));
    }
    
    /**
     * Retourne une instance de fichier
     * @return une instance Config 
     * @see Config
     */
    public Config getConfig() {
        return this.config;
    }   

}