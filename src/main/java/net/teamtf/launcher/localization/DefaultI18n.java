package net.teamtf.launcher.localization;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.teamtf.launcher.core.Engine;
import net.teamtf.launcher.core.I18n;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author Darkyoooooo
 */
public class DefaultI18n implements I18n {
    private final Map<String, String> map = new HashMap<String, String>();
    private final String defaultLangFileName = "zh_CN";
    private final String langFileName;
    
    public DefaultI18n() {
	Locale locale = Locale.getDefault();
	this.langFileName = locale.getLanguage() + "_" + locale.getCountry();
	this.importLanguageData(this.getClass(), "/lang/");
    }
    
    /**
     * Import the language data to the map
     * 
     * @param clazz any class of the addon is OK
     * @param path the path of the folder what the language-files in it
     */
    @Override
    public void importLanguageData(Class<? extends Object> clazz, String path) {
	if(!path.startsWith("/")) {
	    path = "/" + path;
	}
	InputStream stream = clazz.getResourceAsStream(path + "/" + langFileName + ".lang");
	if(stream == null) {
	    Engine.getEngine().getLogger().warn("Unsupported language \'" + langFileName
		    + "\', trying to set as default.");
	    stream = clazz.getResourceAsStream(path + "/" + defaultLangFileName + ".lang");
	}
	this.loadFromInputstream(stream);
    }
    
    /**
     * Get the localized string by a key
     * 
     * @param key key of the localized string
     * @return the localized string (if it is missing, will return the key)
     */
    @Override
    public String getTranslation(String key) {
	String string = map.get(key);
	return string == null ? key : string;
    }
    
    private void loadFromInputstream(InputStream stream) {
	if(stream == null) {
	    throw new RuntimeException("Missing language-file, it should not be happened, contact the author!");
	}
	try {
	    List<String> list = IOUtils.readLines(stream, "utf-8");
	    for(String line : list) {
		if(line.startsWith("#")) {
		    continue;
		}
		try {
		    String[] elements = line.split("=", 2);
		    map.put(elements[0], elements[1]);
		} catch (Exception ex) {
		    ;
		}
	    }
	} catch (IOException e) {
	    ;
	}
    }
}
