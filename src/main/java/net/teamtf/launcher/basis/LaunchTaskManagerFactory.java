package net.teamtf.launcher.basis;

import java.io.File;
import net.teamtf.launcher.util.task.TaskManager;

/**
 * @Author Decker
 */
public class LaunchTaskManagerFactory {

    private File launchFolder;
    private File nativeFolder;
    private File versionFolder;
    private File assetsFoler;

    public LaunchTaskManagerFactory() {
        super();
    }

    public void setLaunchingFolder(File folder) {
        this.launchFolder = folder;
    }

    public TaskManager buildTaskManager() {
        TaskManager result = new TaskManager();
        return result;
    }
}
