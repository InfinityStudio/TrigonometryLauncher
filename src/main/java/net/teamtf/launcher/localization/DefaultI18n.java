package net.teamtf.launcher.localization;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.teamtf.launcher.core.Engine;
import net.teamtf.launcher.core.I18n;
import net.teamtf.launcher.util.Util;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author Darkyoooooo
 */
public class DefaultI18n implements I18n {
    private final Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
    private final Locale locale = Locale.getDefault();
    private final String defaultLangFileName = "zh_CN";
    private String langFileName;
    
    public DefaultI18n() {
	this.langFileName = locale.toString();
	this.importLanguageData(this.getClass(), "/lang/", locale.toString());
    }
    
    /**
     * Import the language data to the map
     * 
     * @param clazz any class of the addon is OK
     * @param path the path of the folder what the language-files in it
     */
    @Override
    public void importLanguageData(Class<? extends Object> clazz, String path, String locale) {
	if(!path.startsWith("/")) {
	    path = "/" + path;
	}
	InputStream stream = clazz.getResourceAsStream(path + "/" + langFileName + ".lang");
	if(stream == null) {
	    Engine.getEngine().getLogger().warn("Unsupported language \'" + langFileName
		    + "\', trying to set as default.");
	    langFileName = defaultLangFileName;
	    stream = clazz.getResourceAsStream(path + "/" + langFileName + ".lang");
	}
	this.loadFromInputstream(stream, locale.toString());
    }
    
    /**
     * Import the language data to the map
     * 
     * @param clazz any class of the addon is OK
     * @param path the path of the folder what the language-files in it
     */
    @Override
    public void importLanguageData(Class<? extends Object> clazz, String path) {
	this.importLanguageData(clazz, path, locale.toString());
    }
    
    /**
     * Import a language-data-map to the map
     * 
     * @param map the language-data-map
     * @param locale the program will use the map by the locale
     */
    @Override
    public void importLaunguaeDataByMap(Map<String, String> map, String locale) {
	if(this.map.get(locale) == null) {
	    this.map.put(locale, map);
	} else {
	    Util.addMapToMap(map, this.map.get(locale));
	}
    }
    
    /**
     * Import a language-data-map to the map
     * 
     * @param map the language-data-map
     */
    @Override
    public void importLaunguaeDataByMap(Map<String, String> map) {
	this.importLaunguaeDataByMap(map, locale.toString());
    }
    
    private void loadFromInputstream(InputStream stream, String locale) {
	if(stream == null) {
	    throw new RuntimeException("Missing language-file, it should not be happened, contact the author!");
	}
	try {
	    Map<String, String> temp = new HashMap<String, String>();
	    List<String> list = IOUtils.readLines(stream, "utf-8");
	    for(String line : list) {
		if(line.startsWith("#")) {
		    continue;
		}
		try {
		    String[] elements = line.split("=", 2);
		    temp.put(elements[0], elements[1]);
		} catch (Exception ex) {
		    ;
		}
	    }
	    if(map.get(locale) == null) {
		map.put(locale, temp);
	    } else {
		Util.addMapToMap(temp, map.get(locale));
	    }
	} catch (IOException e) {
	    ;
	}
    }
    
    /**
     * Get the localized string by a key
     * 
     * @param key key of the localized string
     * @return the localized string (if it is missing, will return the key)
     */
    @Override
    public String getTranslation(String key) {
	String string = map.get(this.langFileName).get(key);
	return string == null ? key : string;
    }
    
    /**
     * Get the localized string by a key and the locale
     * 
     * @param key key of the localized string
     * @param locale the locale
     * @return the localized string (if it is missing, will return the key)
     */
    @Override
    public String getTranslationByLocale(String key, String locale) {
	String string = map.get(locale) == null ? null : map.get(locale).get(key);
	return string == null ? key : string;
    }
}
