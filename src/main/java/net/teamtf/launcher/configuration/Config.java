package net.teamtf.launcher.configuration;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import net.teamtf.launcher.core.Engine;
import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Decker
 */
public class Config {

    private static Config instance;

    private static void init() {
        if (instance == null) {
            instance = new Config();
        }
    }

    public static String getConfig(String configName) {
        init();
        return instance.config.get(configName);
    }

    public static void setConfig(String configName, String value) {
        instance.config.put(configName, value);
        instance.saveTofFile();
    }

    public static void saveAllConfigToFile() throws IOException {
        instance.saveTofFile();
    }
    private File configFile;
    private HashMap<String, String> config;

    private void saveTofFile() {
        try {
            String stringToFile = (String) (new Yaml().dump(this.config));
            FileUtils.writeStringToFile(configFile, stringToFile);
        } catch (Exception e) {
            Engine.getEngine().getLogger().error("Can not write config to file.", e);
        }

    }

    public Config() {
        try {
            configFile = new File(new File(URLDecoder.decode(Engine.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8")).getParentFile().getCanonicalPath()).toPath().resolve("config.yml").toFile();
            if (!configFile.exists()) {
                configFile.createNewFile();
            } else {
                String fileContent = FileUtils.readFileToString(configFile);
                this.config = (HashMap<String, String>) (new Yaml().load(fileContent));
                if (this.config == null) {
                    this.config = new HashMap<>();
                }
            }
        } catch (Exception e) {
            Engine.getEngine().getLogger().fatal("Can not process config file.");
            System.exit(0);
        }

    }

}
