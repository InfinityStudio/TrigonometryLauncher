package net.teamtf.launcher.util;

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

    public static Log logger = LogFactory.getLog(DownloadUtils.class);

    public static void downloadToFile(String url, String filename) throws IOException {
        downloadToFile(new URL(url), new File(filename));

    }

    public static void downloadToFile(URL url, File file) throws IOException {
        logger.info("Downloading " + url + " to " + file);
        FileUtils.copyURLToFile(url, file);
    }
}
