package net.teamtf.launcher.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Darkyoooooo
 */
public class MD5Utils {
    /**
     * Encrypt a string to MD5
     * 
     * @param string the string
     * @return the MD5 of the string
     */
    public static String encryptString(String string){
	return DigestUtils.md5Hex(string).toUpperCase();
    }
    
    /**
     * Encrypt a file to MD5
     * 
     * @param string the file
     * @return the MD5 of the string
     */
    public static String encryptFile(File file) {
	try {
	    return DigestUtils.md5Hex(new FileInputStream(file)).toUpperCase();
	} catch (IOException e) {
	    ;
	}
	return "null";
    }
}
