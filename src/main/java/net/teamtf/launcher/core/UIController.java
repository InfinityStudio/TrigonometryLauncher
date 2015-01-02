package net.teamtf.launcher.core;

import java.awt.Dialog;
import java.awt.Window;

/**
 * This instance class controls all instance of GUI
 * @author Decker
 */
public interface UIController {

    public Window getMainWindow();
    public Dialog getConfigDialog();
    public Dialog getAuthDialog();
    
    public void closeAllWindow();
}
