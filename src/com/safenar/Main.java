package com.safenar;

import com.safenar.java.Marker;
import com.safenar.lang.Keyword;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.*;

@SuppressWarnings("CanBeFinal")
public class Main {

    // TODO: 01.07.2021 Swing
    @TestMethod
    public static void println(Object word){
        System.out.println(word);// TODO: 01.07.2021 turn it into printing through Swing
        log(debugLog.toPath(), word);
    }

    static final StringWriter sw = new StringWriter();
    static PrintWriter pw = new PrintWriter(sw);//stack trace
    public static File debugLog=new File("logs\\debug.log");
    static File storyDir=new File(System.getProperty("user.home")+"\\storypacks\\");
    public static File[] storypacks=storyDir.listFiles();
    static Random rand=new Random();
    static Scanner commands =new Scanner(System.in);
    static public List<Keyword> keywords=new ArrayList<>();

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

    // TODO: 20.05.2021 Threads here
    // TODO: 01.07.2021 unmarshalling data from storypacks
    // TODO: 01.07.2021 *.java files of other packages
    @Marker(id = "file iteration")
    public static void iterateFiles(){//should return file I guess?
        if (storypacks != null) {
            for (File storypack : storypacks) {//iteracja po storypackach
                File[] dirCheck = storypack.listFiles();//wszystkie foldery grupujące
                if (dirCheck != null) {
                    for (File value : dirCheck) {//iteracja po folderach grupujących
                        File[] files = value.listFiles();
                        if (files != null) {
                            for (File file:files) {
                                if (file.getName().startsWith("_")||!file.isFile()) continue;
                                if (value.toString().equals(storypack +"\\keywords")){
                                    try {
                                        keywords.add((Keyword) DataClass.jsonToObject(file));
                                    } catch (BadDataException e) {
                                        e.printStackTrace(pw);
                                        logToDebug(sw.toString());
                                    }
                                }

                            }

                        }else println("Explain to me: WHY is there a folder, if there's nothing inside it?");
                    }
                }else println(storypack.getName()+"is empty. Unless I'm missing something...");
            }
        }else println("No storypacks avaliable. Too bad!");

        println(keywords.toString());
    }
    @Marker(id="input")
    public static ArrayList<String> getInput(){
        return (ArrayList<String>) Arrays.asList(commands.next().split(" "));
    }

    @Marker(id="check")
    public static boolean check(Keyword key){
        String command=getInput().remove(0);
        return command.equalsIgnoreCase(key.getName());
    }

    public static void execute(){
        for (Keyword key : keywords) {
            if (check(key)){
                try {
                    Method method = Class.forName(key.getMethodName().getPackageName()+"."+key.getMethodName().getClassName()).getMethod(key.getMethodName().getMethodName());
                    method.invoke(Class.forName(key.getMethodName().getClassName()), getInput().toArray());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace(pw);
                    logToDebug(sw.toString());
                }// TODO: 20.08.2021 end it.
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Marker(id="main")
    public static void main(String... args) {
        if (!storyDir.exists()){
            try {
                Files.createDirectory(storyDir.toPath());
            } catch (IOException ioException) {
                ioException.printStackTrace(pw);
                logToDebug(sw.toString());
            }
        }
        iterateFiles();
        System.out.println(storyDir.toPath());
        execute();
        println("storypacks = " + Arrays.toString(storypacks));//przeiteruj po tym i znajdź wszystkie foldery i pliki
        println("\n");
    }
}
