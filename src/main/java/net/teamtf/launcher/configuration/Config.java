package net.teamtf.launcher.configuration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import net.teamtf.launcher.core.Engine;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Decker
 */
public class Config {

    private File configFile;
    private HashMap<String, String> config;
    private Log configLogSystem;

    private void saveToFile() {
        try {
            String stringToFile = (String) (new Yaml().dump(this.config));
            FileUtils.writeStringToFile(configFile, stringToFile);
        } catch (Exception e) {
            Engine.getEngine().getLogger().error("Can not write config to file.", e);
        }
    }

    public String getConfig(String key) {
        String res = this.config.get(key);
        if (StringUtils.isEmpty(res)) {
            this.configLogSystem.warn(String.format("No such key called '%s' contained in this config", key));
        }
        return res;
    }
    public void setConfig(String key,String value)
    {
        this.config.put(key, value);
        this.saveToFile();
    }

    @SuppressWarnings("unchecked")
    public Config(String configFilePath) {
        this.configLogSystem = LogFactory.getLog("CONFIG");
        try {
            //I do not know which implementation is better. 
            //But, by following the principle of Occam's Razor, I would using simple one.
            //If exception occurs at here, replace by next implementation.
            configFile = new File(configFilePath);
            //configFile = new File(new File(URLDecoder.decode(Engine.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8")).getParentFile().getCanonicalPath()).toPath().resolve(configFilePath).toFile();

            if (!configFile.exists()) {
                configFile.createNewFile();
            } else {
                String fileContent = FileUtils.readFileToString(configFile);
                Object o = new Yaml().load(fileContent);
                if (!(o instanceof HashMap)) {
                    throw new IOException("Invalid file format. Please check your file format through http://www.yamllint.com/");
                }
                this.config = (HashMap<String, String>) (new Yaml().load(fileContent));
                if (this.config == null) {
                    this.config = new HashMap<>();
                }
            }
        } catch (Exception e) {
            this.configLogSystem.fatal("Can not pass config file.", e);
            System.exit(-1);
        }

    }

}
