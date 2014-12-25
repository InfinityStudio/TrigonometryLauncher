package net.teamtf.launcher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Decker
 */
public class Test {

    public static void main(String[] args) {
        Log log = LogFactory.getLog(Test.class);
        log.debug("hello!debug");
        log.info("hello!info");
        log.warn("hello!warn");
        log.error("hello!error");
    }
}
