package net.teamtf.launcher.util;

import java.util.Locale;

/**
 * @author Darkyoooooo
 */
public class OSUtils {
    /**
     * Get current OS Type
     * 
     * @return the current OS type
     */
    public static OSType getCurrentOSType() {
	String osname = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
	if(osname.contains("win")) {
	    return OSType.WINDOWS;
	} else if (osname.contains("linux")) {
	    return OSType.LINUX;
	} else if (osname.contains("mac")) {
	    return OSType.MACOSX;
	} else {
	    return OSType.OTHER;
	}
    }
    

}
