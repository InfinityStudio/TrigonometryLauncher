package net.teamtf.launcher;

import net.teamtf.launcher.core.Engine;

/**
 *
 * @author Decker
 */
public class Start {
    public static void main(String[] args) throws Exception{
        Engine.initEngine();
        Engine engine=Engine.getEngine();
        engine.start();
    }
}
