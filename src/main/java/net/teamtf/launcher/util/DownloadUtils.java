package net.teamtf.launcher.util;

import net.teamtf.launcher.content.FileContent;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Epix
 * @since 2015/1/5
 */
public class DownloadUtils {
    public static void downloadToFile(String url, File file) throws IOException {
	//WARNING: This file is now imaginary, rebuild it when the FileSystem finished
	File tempdirectory = new File("AppData/Roaming/.tflauncher/temp/" +
	    RandomUtils.nextString(FileContent.TEMPFILE_NAME_MAX_LENGTH));
	
	File path = new File(tempdirectory.getAbsolutePath() + "/" + file.getName());
	FileUtils.copyURLToFile(new URL(url), path);
	FileUtils.moveFile(path, file);
    }
}
