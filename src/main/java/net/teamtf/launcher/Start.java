package net.teamtf.launcher;

import net.teamtf.launcher.core.Engine;
import net.teamtf.launcher.util.DownloadUtil;

/**
 *
 * @author Decker
 */
public class Start {
    public static void main(String[] args) throws Exception{
        Engine.initEngine();
        Engine engine=Engine.getEngine();
        engine.start();
        DownloadUtil thread = new DownloadUtil();
        thread.start();
    }
}
