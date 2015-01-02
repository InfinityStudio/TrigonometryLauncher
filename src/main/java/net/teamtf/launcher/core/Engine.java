package net.teamtf.launcher.core;

import net.teamtf.launcher.basis.gui.DefaultUIControler;
import net.teamtf.launcher.addon.AddonLoader;
import net.teamtf.launcher.basis.DefaultLauncher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.MalformedURLException;

import net.teamtf.launcher.configuration.Config;

/**
 * @Author Decker
 */
public class Engine {
    
    private static Engine instance;
    
    public static void initEngine() throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            instance = new Engine();
        } else {
            throw new IllegalAccessException("Engine has already initialized");
        }
    }
    
    public static Engine getEngine() {
        return instance;
    }
    
    private final Log logger;
    private UIController uiController;
    private Launcher launcher;
    private I18N i18n;
    private final AddonLoader addonLoader;
    private final Config configuration;
    
    Engine() throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
        this.logger = LogFactory.getLog("MainLogger");
        this.configuration = new Config("config.yml");
        this.uiController = new DefaultUIControler();
        this.launcher = new DefaultLauncher();
        
        this.addonLoader = new AddonLoader(this.configuration.getConfig("addonfolder"));
        
    }

    /**
     * Engine start working. 1.load add addons.
     *
     *
     * @throws Exception
     */
    public void start() throws Exception {
        //TODO:Still under develop
        //I just put everything here as possible.
        this.addonLoader.loadFilesFromFolder();
        this.addonLoader.perLoadAllAddons();
        this.uiController.getMainWindow();
        this.addonLoader.loadAllAddons();
        this.uiController.getMainWindow().setVisible(true);
        this.addonLoader.postLoadAllAddons();
        
    }
    
    public Log getLogger() {
        return logger;
    }
    
    public UIController getUIController() {
        return this.uiController;
    }

    /**
     * Receive current launcher of
     *
     * @return launcher which current using
     */
    public Launcher getLauncher() {
        return launcher;
    }
    
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }
    
    public AddonLoader getAddonLoader() {
        return addonLoader;
    }

    /**
     * @return the configuration
     */
    public Config getEngineConfig() {
        return configuration;
    }
    
    public void stop()
    {
        this.uiController.closeAddWindow();
    }

    /**
     * @param uiController the uiController to set
     */
    public void setUiController(UIController uiController) {
        this.uiController = uiController;
    }

    /**
     * @return the i18n
     */
    public I18N getI18n() {
        return i18n;
    }

    /**
     * @param i18n the i18n to set
     */
    public void setI18n(I18N i18n) {
        this.i18n = i18n;
    }
    
}
