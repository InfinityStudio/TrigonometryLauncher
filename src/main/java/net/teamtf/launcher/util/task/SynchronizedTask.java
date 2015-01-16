package net.teamtf.launcher.util.task;

/**
 *
 * @author decker
 */
public abstract class SynchronizedTask implements Task {

    private TaskExecuteException exceptionDuringExecuting;

    public SynchronizedTask() {

    }

    @Override
    public abstract void run();

    @Override
    public Boolean isSynchronized() {
        return true;
    }

    @Override
    public TaskExecuteException getTaskExecuteException() {
        return this.exceptionDuringExecuting;
    }
    
       
    protected void setTaskExecuteException(Exception exception) {
       this.exceptionDuringExecuting=new TaskExecuteException(this, exception);
    }
}
