/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.teamtf.launcher.util.task;

/**
 *
 * @author Decker
 */
public class TaskExecuteException extends RuntimeException {

    private Task faildTask;

    public Task getFaildTask() {
        return this.faildTask;
    }

    public TaskExecuteException(String message) {
        super(message);
    }

    public TaskExecuteException(Task task) {

        this(String.format("Task %s went wrong", task.getName()));
        this.faildTask = task;
    }

    public TaskExecuteException() {
    }

    public TaskExecuteException(Task faildTask, Throwable cause) {
        super(cause);
        this.faildTask = faildTask;
    }

    public TaskExecuteException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskExecuteException(Throwable cause) {
        super(cause);
    }

    public TaskExecuteException(Task faildTask, String message) {
        super(message);
        this.faildTask = faildTask;
    }

    public TaskExecuteException(Task faildTask, String message, Throwable cause) {
        super(message, cause);
        this.faildTask = faildTask;
    }

}
