package net.teamtf.launcher.basis;

import java.io.File;
import java.io.IOException;
import net.teamtf.launcher.util.task.SynchronizedTask;
import net.teamtf.launcher.util.task.TaskManager;
import org.apache.commons.io.FileUtils;

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
        result.insertTask(new SynchronizedTask() {

            @Override
            public void run() {
                try {
                    File [] fileList=versionFolder.listFiles();
                    
                } catch (Exception e) {
                    this.setTaskExecuteException(new IOException("Can not locate jar file."));
                }
            }

            @Override
            public String getName() {
                return "LocateJarFile";
            }
        });
        return result;
    }
}
