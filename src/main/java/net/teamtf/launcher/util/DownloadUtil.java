package net.teamtf.launcher.util;

import net.teamtf.launcher.core.Engine;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Epix
 * @since 2015/1/5
 */
public class DownloadUtil extends Thread {
    public static void downloadToFile(String url, String filename) throws IOException{
        downloadToFile(new URL(url),new File(filename));

    }
    public static void downloadToFile(URL url, File file) throws IOException{
        Engine.getEngine().getLogger().info("Downloading "+url+"to "+file);
        FileUtils.copyURLToFile(url,file);
    }
}
