package net.teamtf.launcher;


import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import net.teamtf.launcher.configuration.Config;
import net.teamtf.launcher.core.Engine;
import org.apache.commons.io.FileUtils;

/**
 * Test main functions. This is just a test for every body their self, thus, do
 * not add this file to version control or even commit it. If you encountered
 * problems such as require commit before you could pull. Use "git stash" to
 * backup your workspace and "git stash pop" after that operation.
 *
 * @author Decker
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Config config=new Config(FileUtils.getFile("config.yml"));
        config.setConfig("launcher_dir",new File(new File(URLDecoder.decode(Engine.class.getProtectionDomain().getCodeSource().getLocation().getPath(), "UTF-8")).getParentFile().getCanonicalPath()).toString());
        System.out.println(config.getConfig("native"));
    }
}
