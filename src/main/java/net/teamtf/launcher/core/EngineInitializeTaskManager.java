package net.teamtf.launcher.core;

import net.teamtf.launcher.util.task.TaskManager;
import net.teamtf.launcher.util.task.TaskUtils;

/**
 *
 * @author decker
 */
public class EngineInitializeTaskManager extends TaskManager{

    public EngineInitializeTaskManager() {
        super();
        TaskUtils.appendSyncTask(this, "LoadAddonsFile", new Runnable() {

            @Override
            public void run()  {
                try {
                    Engine.getEngine().getAddonLoader().loadFilesFromFolder();
                } catch (Exception ex) {
                    System.exit(-1);
                } 
            }
        });
    }
     
}
