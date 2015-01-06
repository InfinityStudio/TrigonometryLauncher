package net.teamtf.launcher.localization;

import java.util.Map;

/**
 *
 * @author Decker
 */
public interface I18n {
    /**
     * Import the language data to the map
     * 
     * @param clazz any class of the addon is OK
     * @param path the path of the folder what the language-files in it
     * @param locale the locale
     */
    public void importLanguageData(Class<? extends Object> clazz, String path, String locale);
    
    /**
     * Import the language data to the map
     * 
     * @param clazz any class of the addon is OK
     * @param path the path of the folder what the language-files in it
     */
    public void importLanguageData(Class<? extends Object> clazz, String path);
    
    /**
     * Import a language-data-map to the map
     * 
     * @param map the language-data-map
     * @param locale the program will use the map by the locale
     */
    public void importLaunguaeDataByMap(Map<String, String> map, String locale);
    
    /**
     * Import a language-data-map to the map
     * 
     * @param map the language-data-map
     */
    public void importLaunguaeDataByMap(Map<String, String> map);
    
    /**
     * Get the localized string by a key
     * 
     * @param key key of the localized string
     * @return the localized string (if it is missing, will return the key)
     */
    public String getTranslation(String key);
    
    /**
     * Get the localized string by a key and the locale
     * 
     * @param key key of the localized string
     * @param locale the locale
     * @return the localized string (if it is missing, will return the key)
     */
    public String getTranslationByLocale(String key, String locale);
}
