package net.teamtf.launcher.core;

import java.io.File;

import net.teamtf.launcher.content.FileContent;
import net.teamtf.launcher.util.OSType;
import net.teamtf.launcher.util.OSUtils;
import net.teamtf.launcher.util.RandomUtils;
import net.teamtf.launcher.util.Utils;

/**
 * @author Darkyoooooo
 */
public class TLFileSystem {
    private final File parentDir,
                       clientDir,
                       userDir,
                       tempDir,
                       crashreportsDir,
                       logDir;
    private final String username;
    
    public TLFileSystem(String username) {
	this.username = username;
	String userHomeDir = System.getProperty("user.home", ".");
	String filename = ".tflauncher";
	OSType type = OSUtils.getCurrentOSType();
	if(type == OSType.WINDOWS) {
	    parentDir = new File(System.getenv("APPDATA"), filename);
	} else if(type == OSType.MACOSX) {
	    parentDir = new File(new File(new File(userHomeDir, "Library"),
		    "Application Support"), "tflauncher");
	} else {
	    parentDir = new File(userHomeDir, filename);
	}
	this.parentDir.mkdir();
	this.clientDir = new File(this.parentDir, "client");
	this.clientDir.mkdir();
	this.userDir = new File(this.parentDir, "user");
	this.userDir.mkdir();
	this.tempDir = new File(this.parentDir, "temp");
	this.tempDir.mkdir();
	this.crashreportsDir = new File(this.parentDir, "crash-report");
	this.crashreportsDir.mkdir();
	this.logDir = new File(this.parentDir, "log");
	this.logDir.mkdir();
    }
    
    /**
     * Get the parent config file
     * 
     * @return the parent config file
     */
    public File getParentConfigFile() {
	return new File(this.userDir, ".config");
    }
    
    /**
     * Get user directory
     * 
     * @return the user directory
     */
    public File getUserDir() {
	return new File(this.userDir, this.username);
    }
    
    /**
     * Get config file
     * 
     * @return the config file
     */
    public File getConfigFile() {
	return new File(new File(this.getUserDir(), "launcher"), ".config");
    }
    
    /**
     * Get the modpack directory
     * 
     * @return the modpack directory
     */
    public File getModpackDir() {
	return new File(this.getUserDir(), "modpacks");
    }
    
    /**
     * Get the list of the modpacks
     * 
     * @return the list of the modpacks
     */
    public File getModpacksListFile() {
	return new File(this.getModpackDir(), ".list");
    }
    
    /**
     * Create a new temp directory
     * 
     * @return the new temp directory
     */
    public File createNewTempDir() {
	return new File(this.tempDir, RandomUtils.nextString(FileContent.TEMPFILE_NAME_MAX_LENGTH));
    }
    
    /**
     * Create a new crash-report file
     * 
     * @return the new crash-report file
     */
    public File createNewCrashreportFile() {
	return new File(this.crashreportsDir, Utils.getCurrentFullyTime().replaceAll(":", "-") + ".txt");
    }
    
    /**
     * Create a new log file
     * 
     * @return the new log file
     */
    public File createNewLogFile() {
	return new File(this.logDir, Utils.getCurrentFullyTime().replaceAll(":", "-") + ".txt");
    }
}
