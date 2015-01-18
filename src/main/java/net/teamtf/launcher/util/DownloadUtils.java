package net.teamtf.launcher.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import net.teamtf.launcher.core.Engine;

import org.apache.commons.io.FileUtils;

/**
 * @author Epix, Darkyoooooo
 * @since 2015/1/5
 */
public class DownloadUtils {
    public static void downloadToFile(String url, File file) throws IOException {
	File tempDir = Engine.getEngine().getFileSystem().createNewTempDir();
	
	File path = new File(tempDir.getAbsolutePath(), file.getName());
	FileUtils.copyURLToFile(new URL(url), path);
	FileUtils.moveFile(path, file);
    }
}
