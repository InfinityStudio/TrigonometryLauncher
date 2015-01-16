package net.teamtf.launcher.util.task;

/**
 * Basic element of task management which responsible for single process.
 *
 * @author decker
 */
public interface Task extends Runnable {

    public String getName();

    public Boolean isSynchronized();

    public TaskExecuteException getTaskExecuteException();
}
