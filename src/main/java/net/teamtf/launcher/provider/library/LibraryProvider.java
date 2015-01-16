package net.teamtf.launcher.provider.library;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;

import net.teamtf.launcher.core.Library;
import net.teamtf.launcher.util.DownloadUtils;
import net.teamtf.launcher.util.LibraryUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Decker
 */
public class LibraryProvider {
    private static Log logger;
    static {
        logger=LogFactory.getLog(LibraryProvider.class);
    }
    
    private HashMap<String, Library> libraryQuery;
    private File libraryFolder;
    private File repoFile;
    private URL repoUrl;
    private String repoContent;


    public LibraryProvider(File libraryFolder,File repoFile, URL repoUrl) throws IOException {
        this.libraryQuery = new HashMap<>();
        this.libraryFolder = libraryFolder;
        this.repoUrl = repoUrl;
        this.repoFile=repoFile;


        //Try to download the latest version of librepo.json
        try {
            //Files.deleteIfExists(FileUtils.getFile(libraryFolder, "librepo.json").toPath());
            DownloadUtils.downloadToFile(repoUrl, FileUtils.getFile(libraryFolder, "librepo.json"));
        } catch (Exception e) {
            logger.warn(String.format("LibProvider:Can not fetch repo.json form %s", repoUrl));
        }
        File repoJsonFile = FileUtils.getFile(libraryFolder, "librepo.json");
        if (!repoJsonFile.exists()) {
            logger.warn("LibProvider:repo.json not exists, you have to connect to internet at first time.");
            throw new IOException("librepo.json not found");
        }

        JsonObject repoJsonObject = new Gson().fromJson(FileUtils.readFileToString(repoJsonFile), JsonObject.class);
        for (JsonElement lib : repoJsonObject.getAsJsonArray("libraries")) {
            String groupId = lib.getAsJsonObject().get("groupId").getAsString();
            String artifactId = lib.getAsJsonObject().get("artifactId").getAsString();
            String version = lib.getAsJsonObject().get("version").getAsString();
            String url = lib.getAsJsonObject().get("url").getAsString();

            Path inferPath = LibraryUtils.resolveLibPath(libraryFolder, groupId, artifactId, version);
            Library l;
            if (inferPath.toFile().exists()) {
                l = new Library(groupId, artifactId, version, url, inferPath.toFile());
            } else {
                l = new Library(groupId, artifactId, version, url, null);
            }
            this.libraryQuery.put(String.format("%s:%s:%s", groupId, artifactId, version), l);

        }

    }

    public File getLibrary(String groupId, String artifactId, String version) throws IOException {
        String libString = String.format("%s:%s:%s", groupId, artifactId, version);
        return this.getLibrary(libString);
    }

    public File getLibrary(String libString) throws IOException {
        if (this.libraryQuery.get(libString).getLibFile() == null||!this.libraryQuery.get(libString).getLibFile().exists()) {
            DownloadUtils.downloadToFile(
                    this.libraryQuery.get(libString).getDownloadURL(),
                    LibraryUtils.resolveLibPath(
                            this.libraryFolder,
                            this.libraryQuery.get(libString).getGroupID(),
                            this.libraryQuery.get(libString).getName(),
                            this.libraryQuery.get(libString).getVersion()
                    ).toString()
            );
        }
        return this.libraryQuery.get(libString).getLibFile();
    }

}
