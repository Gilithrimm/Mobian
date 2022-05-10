package com.safenar;

import com.safenar.java.Marker;
import com.safenar.lang.Keyword;
import com.safenar.lang.Storypack;
import com.safenar.swing.MyFrame;
import com.safenar.util.Directory;
import com.safenar.util.JSONParser;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.*;

@SuppressWarnings("CanBeFinal")
public class Main {
    public static final String version="0.01-alpha";
    public static File debugLog=new File("logs/debug.log");
    public static Logger logger=new Logger(debugLog.toPath());

    static Directory storyDir=new Directory(System.getProperty("user.home"));
    public static Directory[] storypacks=
            Arrays.stream(Objects.requireNonNull(storyDir.listFiles()))
                    .map(Directory::new)
                    .toArray(Directory[]::new);
    static Random rand=new Random();
    static Scanner commands =new Scanner(System.in);
    static public List<Keyword> keywords=new ArrayList<>();
    public static MyFrame frame;

    @Marker(id="main")
    public static void main(String... args) {
        if (!storyDir.exists()){
            try {
                Files.createDirectory(storyDir.toPath());
            } catch (IOException ioException) {
                logger.log(logger.getStackTrace(ioException));
            }
        }
        iterateFiles();
        frame=new MyFrame();

        logger.println("Welcome to Mobian! Write help to get all the commands.");
        String command=getInput().remove(0);
        logger.log(arrayToString(getInput()));

        for (Keyword key : keywords) {
            if (command.equalsIgnoreCase(key.getName())){
                try {
                    Method method = Class.forName(key.getMethodName().getPackageName()+"."+key.getMethodName().getClassName()).getMethod(key.getMethodName().getMethodName());
                    method.invoke(getInput().toArray());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    logger.log(logger.getStackTrace(e));
                }// TODO: 20.08.2021 end it.
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Marker(id = "file iteration")
    public static void iterateFiles(){
        Directory[] files=printlnAndReturn(iterateDirs("Explain to me: WHY is there a folder, " +
                        "if there's nothing inside it?",
                printlnAndReturn(iterateDirs("One of the storypacks is empty. " +
                                "Unless I'm missing something...",
                        printlnAndReturn(iterateDirs(
                                "No storypacks available. Too bad!",
                                printlnAndReturn(storypacks,"")
                        ),"")), "")),""
        );
        for (File file: files){
            if (file.getName().startsWith("_")||!file.isFile()) continue;
            addToList(file, new ArrayList<>(keywords));
        }
        logger.println(files.length);
    }

    private static Directory[] iterateDirs(String msgWhenEmpty, File... files){
        if (files != null) {
            for (File file : files) {
                return (Directory[]) printlnAndReturn(file.listFiles(),"");
            }
        }
        throw new NullPointerException(msgWhenEmpty);
    }

    public static <T> T printlnAndReturn(T returned, String msg){
        if (returned.getClass().isArray())
            logger.println(msg + " " + Arrays.toString((Object[]) returned));
        else logger.println(msg+returned);
        return returned;
    }

    private static void addToList(File file, List<Object> list) {
        if (file.getAbsolutePath().contains("\\keywords\\")){
            try {
                list.add(new JSONParser<>(file, Keyword.class).parse().getAsJsonObject());
            } catch (IOException e) {
                logger.log(logger.getStackTrace(e));
            }
        }
    }

    @TestMethod
    public static String arrayToString(Collection<String> collection) {
        StringBuilder stringBuilder=new StringBuilder();
        for (String text:collection) {
            stringBuilder.append(text).append(" ");
        }
        return stringBuilder.toString();
    }

    @Marker(id="input")
    public static ArrayList<String> getInput(){
        ArrayList<String> stringArrayList=new ArrayList<>();
        Collections.addAll(stringArrayList,commands.next().split(" "));
        return stringArrayList;
    }

    public static Storypack getStorypack(String name){
        return new Storypack(name);
    }
}
