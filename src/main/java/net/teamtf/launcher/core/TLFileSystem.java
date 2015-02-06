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
    private File parent,
                 client,
                 user,
                 temp,
                 crashreport,
                 log;
    public boolean isFirstLaunch;
    
    public TLFileSystem() {
	String userHomeDir = System.getProperty("user.home", ".");
	String filename = ".tflauncher";
	OSType type = OSUtils.getCurrentOSType();
	if(type == OSType.WINDOWS) {
	    parent = new File(System.getenv("APPDATA"), filename);
	} else if(type == OSType.MACOSX) {
	    parent = new File(new File(new File(userHomeDir, "Library"),
	        "Application Support"), "tflauncher");
	} else {
	    parent = new File(userHomeDir, filename);
	}
	client = new File(parent, "client");
	user = new File(parent, "user");
	temp = new File(parent, "temp");
	crashreport = new File(parent, "crash-report");
	log = new File(parent, "log");
	if(parent.exists()) {
	    isFirstLaunch = false;
	} else {
	    isFirstLaunch = true;
	}
	parent.mkdir();
	client.mkdir();
	user.mkdir();
	temp.mkdir();
	crashreport.mkdir();
	log.mkdir();
    }
    
    public File getGameConfigFile() {
	return new File(user, ".gconfig");
    }
    
    public File getLauncherConfigFile() {
	return new File(user, ".lconfig");
    }
    
    public File createNewCrashReportFile() {
	return new File(crashreport, Utils.getCurrentFullyTime() + ".txt");
    }
    
    public File createNewTempDir() {
	return new File(temp, RandomUtils.nextString(24));
    }
}
