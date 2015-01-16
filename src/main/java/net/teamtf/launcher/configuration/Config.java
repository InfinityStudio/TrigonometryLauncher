package net.teamtf.launcher.configuration;

import net.teamtf.launcher.core.Engine;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Decker
 */
public class Config {

    private File configFile;
    private HashMap<String, String> config;
    private Log configLogSystem;
    private final Pattern referencePattern;

    private void saveToFile() {
        try {
            String stringToFile = new Yaml().dump(this.config);
            FileUtils.writeStringToFile(configFile, stringToFile);
        } catch (Exception e) {
            configLogSystem.error("Can not write config to file.", e);
        }
    }

    public String getConfig(String key) {
        key = StringUtils.removeStart(key, "${");
        key = StringUtils.removeEnd(key, "}");

        String res = this.config.get(key);
        if (StringUtils.isEmpty(res)) {
            this.configLogSystem.warn(String.format("No such key called '%s' contained in this config", key));
        }
        if (this.referencePattern.matcher(res).find()) {
            Matcher matcher = this.referencePattern.matcher(res);
            while (matcher.find()) {
                res = matcher.replaceFirst(this.getConfig(matcher.group()));
            }

        }
        return res;
    }

    public void setConfig(String key, String value) {
        this.config.put(key, value);
        this.saveToFile();
    }

    @SuppressWarnings("unchecked")
    public Config(File configFile) {
        this.referencePattern = Pattern.compile("\\$\\{.*?\\}");
        this.configLogSystem = LogFactory.getLog("CONFIG");
        try {
            this.configFile = configFile;

            if (!configFile.exists()) {
                InputStream config = getClass().getClassLoader().getResourceAsStream("defaults/config.yml");
                FileUtils.copyInputStreamToFile(config, configFile);
            } else if (!(new Yaml().load(FileUtils.readFileToString(configFile)) instanceof HashMap)) {
                FileUtils.deleteQuietly(configFile);
                InputStream config = getClass().getClassLoader().getResourceAsStream("defaults/config.yml");
                FileUtils.copyInputStreamToFile(config, configFile);
            }

            String fileContent = FileUtils.readFileToString(configFile);
            this.config = (HashMap<String, String>) (new Yaml().load(fileContent));
            if (this.config == null) {
                this.config = new HashMap<>();
            }

        } catch (Exception e) {
            this.configLogSystem.fatal("Can not pass config file.", e);
            System.exit(-1);
        }

    }

}
