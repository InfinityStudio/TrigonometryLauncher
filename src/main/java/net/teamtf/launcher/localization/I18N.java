package net.teamtf.launcher.localization;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.teamtf.launcher.core.Engine;

import org.apache.commons.io.IOUtils;

/**
 *
 * @author Darkyoooooo
 */
public class I18N {
    private static final Map<String, String> map = new HashMap<String, String>();
    private static String langfileName;
    private static String defaultLangfileName;
    
    static {
	defaultLangfileName = "zh_CN";
	Locale locale = Locale.getDefault();
	langfileName = locale.getLanguage() + "_" + locale.getCountry();
	InputStream stream = I18N.class.getResourceAsStream("/lang/" + langfileName + ".lang");
	if(stream == null) {
	    Engine.getEngine().getLogger().warn("Unsupported language \'" + langfileName
		    + "\', trying to set as default.");
	    stream = I18N.class.getResourceAsStream("/lang/" + defaultLangfileName + ".lang");
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
    
    /**
     * Get the localized string by a key
     * 
     * @param key key of the localized string
     * @return the localized string (if it is missing, will return the key)
     */
    public static String get(String key) {
	String string = map.get(key);
	return string == null ? key : string;
    }
    
    /**
     * @return the localized string
     */
    public static String getLangFileName() {
	return langfileName;
    }
    
    /**
     * @return the default lang file name.
     */
    public static String getDefaultLangfileName() {
	return defaultLangfileName;
    }
}
