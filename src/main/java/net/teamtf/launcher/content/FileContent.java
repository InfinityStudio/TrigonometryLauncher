package net.teamtf.launcher.content;

/**
 * @author Darkyoooooo
 */
public class FileContent {
    public static final int TEMPFILE_NAME_MAX_LENGTH = 24;
    public static final String[] CRASHREPORTS_ANNOTATION = {
	"萌萌萌"
    };
    public static final String[] CRASHREPORTS_TEMPLET = {
	"~~~Trigonometry Launcher - Crash Report~~~",
	"//${ANNOTATION}",
	"",
	"Time: ${TIME}",
	"Description: ${DESCRIPTION}",
	"",
	"${EXCEPTION_TEXT}"
    };
}
