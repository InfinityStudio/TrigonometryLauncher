package net.teamtf.launcher.util;

import java.io.File;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Decker
 */
public class LibraryUtils {

    public static Path resolveLibPath(File libFolder, String groupID, String artifactId, String version) {
        File target=new File(libFolder.getPath());
        for (String element : StringUtils.split(groupID, '.')) {
            target=FileUtils.getFile(target, element);
        }
        target=FileUtils.getFile(target, version);
        target=FileUtils.getFile(target, artifactId);
        return target.toPath();
    }

}
