package net.teamtf.launcher.core;

import net.teamtf.launcher.basis.GUI.DefaultUIControler;
import net.teamtf.launcher.addon.AddonLoader;
import net.teamtf.launcher.basis.DefaultLauncher;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.MalformedURLException;

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

    private Log logger;
    private UIController uiController;
    private Launcher launcher;
    private AddonLoader addonLoader;

    Engine() throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
        this.uiController = new DefaultUIControler();
        this.launcher = new DefaultLauncher();
        this.logger = LogFactory.getLog("MainLogger");
        this.addonLoader = new AddonLoader("addons");

    }

    public void start() throws Exception {
        //TODO:Still under develop
        //I just put everything here as possible.
        this.addonLoader.loadFilesFromFolder();
        this.addonLoader.perLoadAllAddons();
        this.addonLoader.loadAddAddons();
        this.addonLoader.postLoadAllAddons();
    }

    public Log getLogger() {
        return logger;
    }

    public UIController getUIController() {
        return this.uiController;
    }

    public Launcher getLauncher() {
        return launcher;
    }

    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    public AddonLoader getAddonLoader() {
        return addonLoader;
    }

}
