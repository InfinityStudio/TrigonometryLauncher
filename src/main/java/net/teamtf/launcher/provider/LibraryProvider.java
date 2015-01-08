package net.teamtf.launcher.provider;

import java.io.File;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Decker
 */
public class LibraryProvider {

    private HashMap<String, File> libraryQuery;

    public LibraryProvider(File libraryFolder) {
        this.libraryQuery = new HashMap<>();
        for (File jarFile : FileUtils.listFiles(libraryFolder, new String[]{"jar"}, true)) {
            this.libraryQuery.put(jarFile.getName(), jarFile);
        }
    }

    public File getJarFileByName(String name) {
        return this.libraryQuery.get(name);
    }

}
