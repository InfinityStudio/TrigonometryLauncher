package net.teamtf.launcher.configuration;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import net.teamtf.launcher.core.Engine;
import net.teamtf.launcher.statistics.CrashReporter;

import org.apache.commons.io.FileUtils;
import org.yaml.snakeyaml.Yaml;

public class Configuration implements IConfig {
    private File configFile;
    private HashMap<String, Object> map;
    
    @SuppressWarnings("unchecked")
    public Configuration(File configFile, String defaultFileName) {
	this.configFile = configFile;
	try {
	    if(!configFile.exists()) {
		Engine.getEngine().getLogger().debug(defaultFileName + " is missing, trying to create it");
                InputStream config = this.getClass().getClassLoader().getResourceAsStream("defaults/" + defaultFileName + ".yml");
                FileUtils.copyInputStreamToFile(config, configFile);
	    } else if (!(new Yaml().load(FileUtils.readFileToString(configFile)) instanceof HashMap)) {
                FileUtils.deleteQuietly(configFile);
                InputStream config = this.getClass().getClassLoader().getResourceAsStream("defaults/" + defaultFileName + ".yml");
                FileUtils.copyInputStreamToFile(config, configFile);
            }
	    String fileContent = FileUtils.readFileToString(configFile);
            this.map = (HashMap<String, Object>) (new Yaml().load(fileContent));
            if (this.map == null) {
                this.map = new HashMap<>();
            }
	} catch (Exception e) {
	    Engine.getEngine().getLogger().fatal("Can not pass " + defaultFileName + " file.", e);
	    new CrashReporter(e , "Can not pass " + defaultFileName + " file.").pack().save();
	    System.exit(-1);
	}
    }
    
    @Override
    public Object getObject(String key) {
	return map.get(key);
    }
    
    @Override
    public String getString(String key) {
	Object obj = map.get(key);
	if(!(obj instanceof String)) {
	    Engine.getEngine().getLogger().error("The value of the key \'" + key + "\' is not a string!");
	    return null;
	}
	return (String) obj;
    }
    
    @Override
    public Boolean getBoolean(String key) {
	Object obj = map.get(key);
	if(!(obj instanceof Boolean)) {
	    Engine.getEngine().getLogger().error("The value of the key \'" + key + "\' is not a boolean!");
	    return null;
	}
	return (Boolean) obj;
    }

    @Override
    public Integer getInteger(String key) {
	Object obj = map.get(key);
	if(!(obj instanceof Integer)) {
	    Engine.getEngine().getLogger().error("The value of the key \'" + key + "\' is not a integer!");
	    return null;
	}
	return (Integer) obj;
    }

    @Override
    public Double getDouble(String key) {
	Object obj = map.get(key);
	if(!(obj instanceof Double)) {
	    Engine.getEngine().getLogger().error("The value of the key \'" + key + "\' is not a double!");
	    return null;
	}
	return (Double) obj;
    }

    @Override
    public List getList(String key) {
	Object obj = map.get(key);
	if(!(obj instanceof List)) {
	    Engine.getEngine().getLogger().error("The value of the key \'" + key + "\' is not a list!");
	    return null;
	}
	return (List) obj;
    }

    @Override
    public void saveToFile() {
    }

    @Override
    public void setConfig(String key, Object obj) {
	this.map.put(key, obj);
    }
}
