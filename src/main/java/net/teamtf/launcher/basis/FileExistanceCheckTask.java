/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.teamtf.launcher.basis;

import java.io.File;
import java.io.IOException;
import net.teamtf.launcher.util.task.SynchronizedTask;

/**
 *
 * @author Decker
 */
public class FileExistanceCheckTask extends SynchronizedTask {

    private File fileToCheck;
    private String taskName;

    private FileExistanceCheckTask() {
    }

    public FileExistanceCheckTask(File fileToCheck) {
        this.fileToCheck = fileToCheck;
        this.taskName = String.format("CheckFileExistance %s", fileToCheck.getPath());
    }

    public FileExistanceCheckTask(File fileToCheck, String taskName) {
        this.fileToCheck = fileToCheck;
        this.taskName = taskName;

    }

    @Override
    public void run() {
        if (this.fileToCheck == null || !this.fileToCheck.exists()) {
            this.setTaskExecuteException(new IOException(String.format("File %s not exists!", fileToCheck.getPath())));
        }
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
