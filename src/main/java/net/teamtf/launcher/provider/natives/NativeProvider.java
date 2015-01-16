package net.teamtf.launcher.provider.natives;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

/**
 * @author Decker
 */
public final class NativeProvider {
    File nativeFolder;
    URL repoUrl;

    /**
     * @param nativeFolder
     * @param repoUrl
     */
    public NativeProvider(File nativeFolder, URL repoUrl) {
        this.nativeFolder = nativeFolder;
        this.repoUrl = repoUrl;
    }

    public Path getNativePath() {
        return this.nativeFolder.toPath();
}
}
