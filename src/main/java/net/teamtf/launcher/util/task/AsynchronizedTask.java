package net.teamtf.launcher.util.task;

/**
 *
 * @author decker
 */
public abstract class AsynchronizedTask implements Task{

    protected Boolean successfullyExecuted;

    public AsynchronizedTask() {
        this.successfullyExecuted=false;
    }
     
    @Override
    public abstract void run();
    
    @Override
    public Boolean isSynchronized()
    {
        return false;
    }
    
    @Override
    public Boolean isSuccessfullyExecuted()
    {
        return this.successfullyExecuted;
    }
    
}
