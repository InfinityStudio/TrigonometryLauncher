package net.teamtf.launcher.util;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
    public static String nextString(int length) {
	return RandomStringUtils.randomAlphanumeric(length).toLowerCase();
    }
    
    public static int nextInteger(int max) {
	return new Random().nextInt(max);
    }
}
