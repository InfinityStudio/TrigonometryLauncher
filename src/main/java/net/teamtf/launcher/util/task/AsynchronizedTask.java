package net.teamtf.launcher.util.task;

/**
 *
 * @author decker
 */
public abstract class AsynchronizedTask implements Task {

    protected TaskExecuteException exceptionDuringExecuting;

    public AsynchronizedTask() {

    }

    @Override
    public abstract void run();

    @Override
    public Boolean isSynchronized() {
        return false;
    }

    @Override
    public TaskExecuteException getTaskExecuteException() {
        return this.exceptionDuringExecuting;
    }

    protected void setTaskExecuteException(Exception exception) {
        this.exceptionDuringExecuting = new TaskExecuteException(this, exception);
    }

}
