package net.teamtf.launcher.basis.GUI;

import net.teamtf.launcher.core.UIController;

import javax.swing.*;

/**
 * Created by Decker on 2014/12/27.
 */
public class DefaultUIControler implements UIController {
    @Override
    public JFrame getMainWindow() {
        return null;
    }

    @Override
    public JDialog getConfigDialog() {
        return null;
    }

    @Override
    public JDialog getAuthDialog() {
        return null;
    }
}
