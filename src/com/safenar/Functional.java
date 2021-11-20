package com.safenar;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public final class Functional {
    static final StringWriter sw = new StringWriter();
    static PrintWriter pw = new PrintWriter(sw);//stack trace
    public static File debugLog=new File("logs\\debug.log");
    static File defaultStoryDir=new File("\\storypacks\\");
    static File storyDir=new File(System.getProperty("user.home"),defaultStoryDir.toString());

    @TestMethod
    public static void println(Object word){
        System.out.println(word);
        log(debugLog.toPath(), word);
    }

    @TestMethod
    public static void logToDebug(Object logs) {
        log(debugLog.toPath(), logs);
    }
    @TestMethod
    public static void log(Path logFile, Object logs){
        try {
            byte[] bytes = (LocalDateTime.now()+": "+ logs.toString()+"\n").getBytes();
            if (!logFile.toFile().exists()) Files.createFile(logFile);
            Files.write(logFile, bytes, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getStackTrace(Exception e){
        e.printStackTrace(pw);
        return sw.toString();
    }
}
