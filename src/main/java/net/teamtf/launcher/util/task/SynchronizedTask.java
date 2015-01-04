package net.teamtf.launcher.util.task;

/**
 *
 * @author decker
 */
public abstract class SynchronizedTask implements Task {

    protected Boolean successfullyExecuted;

    public SynchronizedTask() {
        this.successfullyExecuted = false;
    }

    @Override
    public abstract void run();

    @Override
    public Boolean isSynchronized() {
        return true;
    }

    @Override
    public Boolean isSuccessfullyExecuted() {
        return this.successfullyExecuted;
    }
}
