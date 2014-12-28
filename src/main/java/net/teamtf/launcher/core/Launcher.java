package net.teamtf.launcher.core;

import net.teamtf.launcher.util.task.TaskManager;

/**
 * Created by Decker on 2014/12/27.
 */
public interface Launcher {
    public void launch();
    public TaskManager getLaunchTaskManager();
}
