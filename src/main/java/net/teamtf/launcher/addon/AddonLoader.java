package net.teamtf.launcher.addon;

import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Decker
 */
public final class AddonLoader {

    private final File addonsFolder;

    public AddonLoader(String addonsFolerPath) {
        this.addonsFolder = new File(addonsFolerPath);
        if (!addonsFolder.isDirectory()) {
            throw new IllegalArgumentException("path is not a directory");
        }

    }

}
