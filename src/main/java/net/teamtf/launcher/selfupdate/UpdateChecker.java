package net.teamtf.launcher.selfupdate;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import net.teamtf.launcher.core.Engine;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author Darkyoooooo
 */
public class UpdateChecker {
    private static final String CHECK_LINK = "https://raw.githubusercontent.com/Trigonometry-F/TrigonometryLauncher/dev/version.json";
    private boolean hasUpdate;
    private String newVersion;
    
    public void checkForUpdate() {
	try {
	    URL url = new URL(CHECK_LINK);
	    URLConnection connection = url.openConnection();
	    connection.setConnectTimeout(5000);
	    connection.setDoInput(true);
	    StringBuffer buffer = new StringBuffer();
	    for (String line : IOUtils.readLines(url.openStream())) {
		buffer.append(line.trim());
	    }
	    
	    String versionToCheck;
	    VersionBean version = new Gson().fromJson(buffer.toString(), VersionBean.class);
	    if(Engine.getEngine().getLauncherConfig().getBoolean("launcher.version.enableBetaVersion")) {
		versionToCheck = version.getLastestBetaVersion();
	    } else {
		versionToCheck = version.getLastestReleaseVersion();
	    }
	    String[] s1 = ProgramVersion.getFullVersion().split(".");
	    String[] s2 = versionToCheck.split(".");
	    if(s1.length != s2.length) {
		Engine.getEngine().getLogger().error("Failed to check up the version");
		hasUpdate = false;
		return;
	    }
	    for(int i = 0; i < s1.length; i++) {
		if(Integer.valueOf(s1[i]) < Integer.valueOf(s2[i])) {
		    hasUpdate = true;
		    newVersion = versionToCheck;
		}
	    }
	} catch (IOException ex) {
	    Engine.getEngine().getLogger().error("Failed to check up the version", ex);
	}
    }
    
    public boolean hasNewUpdate() {
	return hasUpdate;
    }
    
    public String getNewVersion() {
	return newVersion;
    }
    
    private class VersionBean {
	private String lastestBuildVersion,
	               lastestBetaVersion,
	               lastestReleaseVersion;
	
	public void setLastestReleaseVersion(String string) {
	    lastestReleaseVersion = string;
	}
	
	public String getLastestReleaseVersion() {
	    return lastestReleaseVersion;
	}
	
	public void setLastestBetaVersion(String string) {
	    lastestBetaVersion = string;
	}
	
	public String getLastestBetaVersion() {
	    return lastestBetaVersion;
	}
	
	public void setLastestBuildVersion(String string) {
	    lastestBuildVersion = string;
	}
	
	public String getLastestBuildVersion() {
	    return lastestBuildVersion;
	}
    }
}
