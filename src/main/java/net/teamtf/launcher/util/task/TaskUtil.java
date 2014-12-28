package net.teamtf.launcher.util.task;

/**
 *
 * @author decker
 */
public class TaskUtil {

    public static void appendAsyncTask(TaskManager taskManager, String taskName, final Runnable runnable) {
        final Runnable r = runnable;
        final String t = taskName;

        taskManager.insertTask(new AsynchronizedTask() {

            @Override
            public void run() {
                r.run();
            }

            @Override
            public String getName() {
                return t;
            }
        });

    }

    public static void appendSyncTask(TaskManager taskManager, String taskName, Runnable runnable) {
        final Runnable r = runnable;
        final String t = taskName;

        taskManager.insertTask(new SynchronizedTask() {

            @Override
            public void run() {
                r.run();
            }

            @Override
            public String getName() {
                return t;
            }
        });
    }
}
