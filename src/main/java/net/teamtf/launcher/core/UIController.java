package net.teamtf.launcher.core;

import javax.swing.*;

/**
 * This instance class controls all instaces of GUI
 * @author Decker
 */
public interface UIController {

    public JFrame getMainWindow();
    public JDialog getConfigDialog();
    public JDialog getAuthDialog();
}
