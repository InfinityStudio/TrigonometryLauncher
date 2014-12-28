package net.teamtf.launcher.basis;

import net.teamtf.launcher.core.Launcher;
import net.teamtf.launcher.util.task.SynchronizedTask;
import net.teamtf.launcher.util.task.TaskManager;

/**
 * @Author Decker
 */
public class DefaultLauncher implements Launcher {
    private TaskManager launchTaskManager;

    public DefaultLauncher() {
        this.launchTaskManager = new TaskManager();
        this.launchTaskManager.insertTask(new SynchronizedTask() {
            @Override
            public void run() {

            }

            @Override
            public String getName() {
                return null;
            }
        });
    }

    @Override
    public void launch() {
        this.launchTaskManager.executeTasks();
    }

    @Override
    public TaskManager getLaunchTaskManager() {
        return this.launchTaskManager;
    }
}
