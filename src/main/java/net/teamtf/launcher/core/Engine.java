package net.teamtf.launcher.core;

import java.io.File;
import java.net.URLDecoder;

import net.teamtf.launcher.addon.AddonLoader;
import net.teamtf.launcher.auth.AuthClient;
import net.teamtf.launcher.basis.DefaultLauncher;
import net.teamtf.launcher.basis.gui.DefaultUIControler;
import net.teamtf.launcher.configuration.Configuration;
import net.teamtf.launcher.configuration.IConfig;
import net.teamtf.launcher.localization.DefaultI18n;
import net.teamtf.launcher.localization.I18n;
import net.teamtf.launcher.provider.assests.AssestsProvider;
import net.teamtf.launcher.provider.library.LibraryProvider;
import net.teamtf.launcher.provider.natives.NativeProvider;
import net.teamtf.launcher.provider.version.VersionProvider;
import net.teamtf.launcher.selfupdate.UpdateChecker;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Decker
 */
public class Engine {

    private static Engine instance;

    public static void initEngine() throws Exception {
        if (instance == null) {
            instance = new Engine();
        } else {
            throw new IllegalAccessException("Engine has already initialized");
        }
    }

    public static Engine getEngine() {
        return instance;
    }

    private final File gameDir;
    private final File launcherDir;
    private final Log logger;
    private final TLFileSystem fileSystem;
    private final UpdateChecker updateChecker;
    private UIController uiController;
    private Launcher launcher;
    private I18n i18n;
    private final AddonLoader addonLoader;
    private final IConfig launcherConfiguration, gameConfiguration;
    private AuthClient authClient;
    private LibraryProvider libraryProvider;
    private AssestsProvider assestsProvider;
    private NativeProvider nativeProvider;
    private VersionProvider versionProvider;

    private Engine() throws Exception {
        this.launcherDir = new File(new File(URLDecoder.decode(Engine.class.getProtectionDomain().getCodeSource().getLocation().getPath(),
        	"UTF-8")).getParentFile().getCanonicalPath());

        this.logger = LogFactory.getLog("MainLogger");
        this.fileSystem = new TLFileSystem();
        
        this.launcherConfiguration = new Configuration(this.fileSystem.getLauncherConfigFile(), "launcherconfig");
        this.launcherConfiguration.setConfig("launcher.dir", this.launcherDir.getAbsolutePath());
        this.gameConfiguration = new Configuration(this.fileSystem.getGameConfigFile(), "gameconfig");
        
        this.gameDir = FileUtils.getFile(this.launcherConfiguration.getString("game.dir"));
        
        this.updateChecker = new UpdateChecker();
        
        this.i18n = new DefaultI18n();
        this.logger.info(this.i18n.getTranslation("lang.welcome"));
        
        this.uiController = new DefaultUIControler();
        this.launcher = new DefaultLauncher();

        this.addonLoader = new AddonLoader(this.launcherConfiguration.getString("addon.dir"));
    }

    /**
     * Engine start working. 1.load add addons.
     *
     * @throws Exception
     */
    public void start() throws Exception {
        //TODO:Still under develop
        //I just put everything here as possible.
        this.addonLoader.loadFilesFromFolder();
        this.addonLoader.perLoadAllAddons();
        this.getUiController().getMainWindow();
        this.addonLoader.loadAllAddons();
        this.getUiController().getMainWindow().setVisible(true);
        this.addonLoader.postLoadAllAddons();

    }

    public Log getLogger() {
        return this.logger;
    }
    
    public TLFileSystem getFileSystem() {
	return this.fileSystem;
    }

    public UIController getUIController() {
        return this.getUiController();
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
     * @return the launcher configuration
     */
    public IConfig getLauncherConfig() {
        return this.launcherConfiguration;
    }

    public void stop() {
        this.getUiController().closeAllWindow();
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
    public I18n getI18n() {
        return i18n;
    }

    /**
     * @param i18n the i18n to set
     */
    public void setI18n(I18n i18n) {
        this.i18n = i18n;
    }

    /**
     * @return the gameDir
     */
    public File getGameDir() {
        return gameDir;
    }

    public UIController getUiController() {
        return uiController;
    }

    public AuthClient getAuthClient() {
        return authClient;
    }

    public void setAuthClient(AuthClient authClient) {
        this.authClient = authClient;
    }

    public LibraryProvider getLibraryProvider() {
        return libraryProvider;
    }

    public void setLibraryProvider(LibraryProvider libraryProvider) {
        this.libraryProvider = libraryProvider;
    }
}
