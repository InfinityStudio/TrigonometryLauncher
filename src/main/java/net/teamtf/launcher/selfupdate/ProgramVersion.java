package net.teamtf.launcher.selfupdate;

/**
 * @author Darkyoooooo
 */
public final class ProgramVersion {
    private static final String FULL_VERSION = "1.2.9.1";
    
    public static String getFullVersion() {
	return FULL_VERSION;
    }
    
    public static String getMajorVersion() {
	return FULL_VERSION.split(".")[0];
    }
    
    public static String getBuiltMonth() {
	return FULL_VERSION.split(".")[1];
    }
    
    public static String getBuiltDay() {
	return FULL_VERSION.split(".")[2];
    }
    
    public static String getBuildVersion() {
	return FULL_VERSION.split(".")[3];
    }
}
