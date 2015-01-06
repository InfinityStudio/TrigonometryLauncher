package net.teamtf.launcher.util.task;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This list provide a pattern to execute function which we highly recommend to
 * use
 *
 * @author decker
 */
public class TaskManager {

    private final LinkedList<Task> tasks;

    public TaskManager() {
        this.tasks = new LinkedList<>();
    }

    public Void executeTasks() {

        LinkedList<Task> reversedList = (LinkedList<Task>) this.tasks.clone();
        Collections.reverse(this.tasks);

        ExecutorService syncPool = Executors.newSingleThreadExecutor();
        ExecutorService asyncPool = Executors.newCachedThreadPool();

        for (Task task : reversedList) {
            if (task.isSynchronized()) {
                syncPool.execute(task);
                if (task.getTaskExecuteException()!=null) {
                    throw task.getTaskExecuteException();
                }
            } else {
                asyncPool.execute(task);
            }
        }
        syncPool.shutdown();
        asyncPool.shutdown();
        return null;
    }

    /**
     * Insert task at the end of task list
     *
     * @param task the task which you want insert
     */
    public void insertTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Insert task at certain index of task list
     *
     * @param index the sequence you want your task execute
     * @param task the task which you want insert
     */
    public void insertTask(Integer index, Task task) {
        this.tasks.add(index, task);
    }

    /**
     * Remove task which at the end of task list
     *
     * @param index the sequence of task
     */
    public void removeTask(Integer index) {
        this.tasks.remove(index.intValue());
    }

    /**
     * Receive index of certain task through task.
     *
     * @param taskName of task
     * @return first index of task which hold that name, -1 if not exists.
     */
    public Integer getTaskSequenceByName(String taskName) {
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getName().equals(taskName)) {
                return i + 1;
            }
        }
        return -1;
    }

}
