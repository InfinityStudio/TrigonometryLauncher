package net.teamtf.launcher.localization;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.teamtf.launcher.core.Engine;
import net.teamtf.launcher.util.MapUtils;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author Darkyoooooo
 */
public class DefaultI18n implements I18n {
    private final Map<String, Map<String, String>> map;
    private final Locale locale;
    private final String defaultLangFileName;
    
    public DefaultI18n() {
	this.map = new HashMap<String, Map<String, String>>();
	this.locale = Locale.getDefault();
	this.defaultLangFileName = "zh_CN";
	
	this.importLanguageData(this.getClass(), "/lang/", locale.toString());
    }
    
    /**
     * Import the language data to the map
     * 
     * @param clazz any class of the addon is OK
     * @param path the path of the folder what the language-files in it (MUST START WITH '/')
     */
    @Override
    public void importLanguageData(Class<? extends Object> clazz, String path, String locale) {
	InputStream stream = clazz.getResourceAsStream(path + "/" + locale + ".lang");
	if(stream == null) {
	    Engine.getEngine().getLogger().warn("Unsupported language \'" + locale
		    + "\', trying to set as defaults.");
	    stream = clazz.getResourceAsStream(path + "/" + defaultLangFileName + ".lang");
	    this.loadFromInputstream(stream, defaultLangFileName);
	} else {
	    this.loadFromInputstream(stream, locale);
	}
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
	    MapUtils.addMapToMap(map, this.map.get(locale));
	}
    }
    
    /**
     * Import a language-data-map to the map by current locale 
     * 
     * @param map the language-data-map
     */
    @Override
    public void importLaunguaeDataByMap(Map<String, String> map) {
	this.importLaunguaeDataByMap(map, locale.toString());
    }
    
    /**
     * Load lang-file from an InputStream
     * 
     * @param stream the InputStream
     * @param locale the locale
     * @throws RuntimeException when the InputStream is null
     */
    private void loadFromInputstream(InputStream stream, String locale) {
	if(stream == null) {
	    throw new RuntimeException("Missing language-file, it should not be happened, "
	    	+ "contact the author!");
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
		    temp.put(elements[0].trim(), elements[1].trim());
		} catch (Exception ex) {
		    ;
		}
	    }
	    if(map.get(locale) == null) {
		map.put(locale, temp);
	    } else {
		MapUtils.addMapToMap(temp, map.get(locale));
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
	String string = map.get(this.locale.toString()).get(key);
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

    /**
     * Get the current locale
     * 
     * @return the instance of current locale
     */
    @Override
    public Locale getCurrentLocale() {
	return this.locale;
    }
}
