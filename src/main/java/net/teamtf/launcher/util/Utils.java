package net.teamtf.launcher.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Darkyoooooo
 */
public class Utils {
    /**
     * Get current time (fully)
     * 
     * @return current time: yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentFullyTime() {
	return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
    }
}
