package net.teamtf.launcher.statistics;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import net.teamtf.launcher.content.FileContent;
import net.teamtf.launcher.core.Engine;
import net.teamtf.launcher.util.RandomUtils;
import net.teamtf.launcher.util.Utils;

/**
 *
 * @author Decker, Darkyoooooo
 */
public class CrashReporter {
    private final Throwable throwable;
    private final String description;
    private final StringBuffer buffer;
    
    public CrashReporter(Throwable throwable, String description) {
	this.throwable = throwable;
	this.description = description;
	this.buffer = new StringBuffer("");
    }
    
    public CrashReporter pack() {
	for(String line : FileContent.CRASHREPORTS_TEMPLET) {
	    if(line.contains("${ANNOTATION}")) {
		 buffer.append(StringUtils.replace(line, "${ANNOTATION}",
			 FileContent.CRASHREPORTS_ANNOTATION[RandomUtils.nextInteger(FileContent.CRASHREPORTS_ANNOTATION.length)]));
	    } else if(line.contains("${TIME}")) {
	        buffer.append(StringUtils.replace(line, "${TIME}", Utils.getCurrentFullyTime()));
	    } else if(line.contains("${DESCRIPTION}")) {
	        buffer.append(StringUtils.replace(line, "${DESCRIPTION}", this.description));
	    } else if(line.contains("${EXCEPTION_TEXT}")) {
		StringBuffer buffer2 = new StringBuffer("");
		for(StackTraceElement element : this.throwable.getStackTrace()) {
		    buffer2.append(element.toString());
		}
		buffer.append(StringUtils.replace(line, "${EXCEPTION_TEXT}", buffer2.toString()));
	    } else {
		buffer.append(line);
	    }
	    buffer.append("\n");
	}
	return this;
    }
    
    public void save() {
	//WARNING: This file is now imaginary, rebuild it when the FileSystem finished
	File file = new File(String.format("AppData/Roaming/.tflauncher/crash-reports/%s.txt",
		Utils.getCurrentFullyTime().replaceAll(":", "-")));
        
	try {
	    file.createNewFile();
	    FileUtils.writeStringToFile(file, this.buffer.toString());
	} catch (IOException e) {
	    Engine.getEngine().getLogger().error("Could not creat crash-report file!");
	}
    }
}
