package com.safenar;

import com.safenar.core.Keyword;
import com.safenar.java.Marker;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.*;

import static com.safenar.Functional.*;

@SuppressWarnings("CanBeFinal")
public class Main implements Initializer {
    /*
    Some TODOs I made at the beginning of this project. I'm still thinking about sense of them hier.
    Nonetheless, after I do everything from them, i'l delete them. bruh.
     TODO: 20.05.2021 Threads
     TODO: 01.07.2021 unmarshalling data from storypacks (nearly done)
     TODO: 01.07.2021 *.java files of other packages
    */

    public static final String version="0.01-alpha";
    private static final Initializer main=new Main();
    public static final ArrayList<Initializer> inits=new ArrayList<>();
    public static final char[] ALPHABET="qwertyuiopasdfghjklzxcvbnm".toUpperCase().toCharArray();
    public static File[] defStorypacks=defaultStoryDir.listFiles();
    public static File[] storypacks=storyDir.listFiles();
    static Random rand=new Random();
    static Scanner commands =new Scanner(System.in);
    static public List<Keyword> keywords=new ArrayList<>();

    private Main() {}

    public static Initializer getMain() {
        return main;
    }

    @Marker(id="main")
    public static void main(String... args) {
        if (!debugLog.delete()) println("Old logs still hier. Just sayin'");
        long start=System.currentTimeMillis();
        getMain().load();
        getMain().init();
        println(System.currentTimeMillis()-start);
    }

    @Override
    public void load() {
        if (!storyDir.exists()){
            try {
                Files.createDirectory(storyDir.toPath());
            } catch (IOException ioException) {
                logToDebug(getStackTrace(ioException));
            }
        }
        iterateFiles();
    }

    @Marker(id = "file iteration")
    public static void iterateFiles(){
        if (storypacks != null) {
            for (File storypack : storypacks) {//iteracja po storypackach
                File[] dirCheck = storypack.listFiles();//wszystkie foldery grupujące
                if (dirCheck != null) {
                    for (File value : dirCheck) {//iteracja po folderach grupujących
                        File[] files = value.listFiles();
                        if (files != null) {
                            for (File file:files) {
                                if (file.getName().startsWith("_")||!file.isFile()) continue;
                                addToList(file, keywords);
                            }
                        }else println("Explain to me: WHY is there a folder, if there's nothing inside it?");
                    }
                }else println(storypack.getName()+"is empty. Unless I'm missing something...");
            }
        }else println("No storypacks avaliable. Too bad!");
    }

    private static void addToList(File file, List<Keyword> list) {
        if (file.getAbsolutePath().contains("\\keywords\\")){
            list.add((Keyword) DataClass.jsonToObject(file));
        }
    }

    @Override
    public void init() {
        for (Keyword key : keywords) {
            if (check(key)){
                try {
                    Method method = Class.forName(key.getMethodName().getPackageName()+key.getMethodName().getClassName()).getMethod(key.getMethodName().getMethodName());
                    method.invoke(getInput().toArray());
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    logToDebug(getStackTrace(e));
                }// TODO: 20.08.2021 end it.
                catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Marker(id="check")
    /*sth is wrong with calling of this method. it goes like:
    * -println
    * -input
    * -println
    * -input
    * -input
    * -method call
    * where it should be like:
    * -println
    * -input
    * -method call
    *
    * help me pls im dying it kills me i feel just       pain
    * */
    public static boolean check(Keyword key){
        println("Welcome to Mobian! Write \"help\" or \"\\h\" to get all the commands.");
        ArrayList<String> list=getInput();
        String command=list.remove(0);
        logToDebug(arrayToString(list));
        return command.equalsIgnoreCase(key.getName());
    }

    @Marker(id="input")
    public static ArrayList<String> getInput(){
        ArrayList<String> stringArrayList=new ArrayList<>();
        Collections.addAll(stringArrayList,commands.next().split(" "));
        return stringArrayList;
    }

    @TestMethod
    public static String arrayToString(Collection<String> collection) {
        StringBuilder stringBuilder=new StringBuilder();
        for (String text:collection) {
            stringBuilder.append(text).append(" ");
        }
        return stringBuilder.toString();
    }
    @TestMethod
    public static String arrayToString(String[] array){
        return arrayToString(Arrays.stream(array).toList());
    }
}
