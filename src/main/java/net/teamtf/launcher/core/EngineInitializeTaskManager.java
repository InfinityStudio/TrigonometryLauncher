package net.teamtf.launcher.core;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.teamtf.launcher.util.task.TaskManager;
import net.teamtf.launcher.util.task.TaskUtil;

/**
 *
 * @author decker
 */
public class EngineInitializeTaskManager extends TaskManager{

    public EngineInitializeTaskManager() {
        super();
        TaskUtil.appendSyncTask(this, "LoadAddonsFile", new Runnable() {

            @Override
            public void run()  {
                try {
                    Engine.getEngine().getAddonLoader().loadFilesFromFolder();
                } catch (Exception ex) {
                    System.exit(0);
                } 
            }
        });
    }
     
}