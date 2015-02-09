package net.teamtf.launcher.selfupdate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author Darkyoooooo
 * TODO:_(:з」∠)_
 */
public class Relauncher {
    public static String getRunningPath() {
	try {
	    String path = Relauncher.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	    path = path.replace("+", URLEncoder.encode("+", "UTF-8"));
	    return URLDecoder.decode(path, "UTF-8");
	} catch (UnsupportedEncodingException e) {
	    return null;
	}
    }
}
