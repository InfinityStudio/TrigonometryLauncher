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

    public TaskExecuteException(String message) {
        super(message);
    }

    public TaskExecuteException(Task task) {

        this(String.format("Task %s went wrong", task.getName()));
        this.faildTask = task;
    }

    public TaskExecuteException() {
    }

}
