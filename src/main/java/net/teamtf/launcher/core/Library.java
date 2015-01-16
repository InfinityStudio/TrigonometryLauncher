package net.teamtf.launcher.core;

import java.io.File;

/**
 *
 * @author Decker
 */
public final class Library {

    private final String groupID;
    private final String name;
    private final String version;
    private final String downloadURL;
    private  File LibFile;

    public Library(String groupID, String name, String version, String downloadURL, File libFile) {
        this.groupID = groupID;
        this.name = name;
        this.version = version;
        this.downloadURL = downloadURL;
        this.LibFile = libFile;
    }

    /**
     * @return the groupID
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the downloadURL
     */
    public String getDownloadURL() {
        return downloadURL;
    }

    /**
     * @return the LibFile
     */
    public File getLibFile() {
        return LibFile;
    }

    /**
     * @param LibFile the LibFile to set
     */
    public void setLibFile(File LibFile) {
        this.LibFile = LibFile;
    }

}
