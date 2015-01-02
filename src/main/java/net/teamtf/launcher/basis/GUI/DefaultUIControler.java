package net.teamtf.launcher.basis.gui;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.teamtf.launcher.core.UIController;

import javax.swing.*;
import net.teamtf.launcher.core.Engine;

/**
 * Created by Decker on 2014/12/27.
 */
public class DefaultUIControler implements UIController {

    MainFrame mainFrame;

    public DefaultUIControler()  {
        try {
            EventQueue.invokeAndWait(new Runnable() {
                
                @Override
                public void run() {
                    mainFrame = new MainFrame();
                }
            });
        } catch (InterruptedException | InvocationTargetException ex) {
            Engine.getEngine().getLogger().fatal("Cant start up main window", ex);
        }

    }

    @Override
    public JFrame getMainWindow() {
        return this.mainFrame;
    }

    @Override
    public JDialog getConfigDialog() {
        return null;
    }

    @Override
    public JDialog getAuthDialog() {
        return null;
    }

    @Override
    public void closeAllWindow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
