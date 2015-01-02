package net.teamtf.launcher.core;

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
     */
    public void importLanguageData(Class<? extends Object> clazz, String path);
    
    /**
     * Get the localized string by a key
     * 
     * @param key key of the localized string
     * @return the localized string (if it is missing, will return the key)
     */
    public String getTranslation(String key);
}
