package com.safenar;

import com.safenar.data.*;
import com.safenar.java.Marker;

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

import javax.sound.sampled.*;

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
    static Player player;
    static Enemy testEnemy;
    static Random rand=new Random();
    static Scanner commands =new Scanner(System.in);
    static List<Keyword> keywords=new ArrayList<>();
    static String input;
    static List<File> music=new ArrayList<>(10);
    static ArrayList<Location> locations=new ArrayList<>();

    static {
        player=new Player("Gili",randRace(),100,10,10,10,10,Item.NO_ITEM, Item.BASE_INVENTORY);
        testEnemy=new Enemy("test enemy",randRace(),10,5,5,3,3,Item.NO_ITEM, new Item[]{Item.NO_ITEM, Item.NO_ITEM});
    }

    @TestMethod
    static Race randRace(){
        List<Race> race= Arrays.asList(Race.values());
        int raceRand=rand.nextInt(5);//NPEx bc y not?
        return race.get(raceRand);
    }

    @TestMethod
    public static void log(Object logs) {
        log(debugLog.toPath(), logs);
    }
    @TestMethod
    public static void log(Path logFile, Object logs){
        try {
            byte[] bytes = (LocalDateTime.now()+": "+ logs.toString()+"\n").getBytes();
            if (!logFile.toFile().exists()) Files.createFile(logFile);
            Files.write(logFile, bytes, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace(pw);
            log(sw.toString());
        }
    }
    @TestMethod
    public static void playMusic(File musicFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            ex.printStackTrace(pw);
            log(sw.toString());
        }
    }

    // TODO: 20.05.2021 Threads here
    // TODO: 28.05.2021 checking keywords in /switch(input) case keywords[i].name->{anything}/
    // TODO: 01.07.2021 unmarshalling data from storypacks
    // TODO: 01.07.2021 *.java files of other packages
    @Marker(id = "file iteration")
    public static void iterateFiles(){//should return file i guess?
        if (storypacks != null) {
            for (File storypack : storypacks) {//iteracja po storypackach
                File[] dirCheck = storypack.listFiles();//wszystkie foldery grupujące
                if (dirCheck != null) {
                    for (File value : dirCheck) {//iteracja po folderach grupujących
                        File[] files = value.listFiles();
                        if (files != null) {
                            if (value.toString().equals(storypack + "\\locations")) {//lokacje
                                for (File file : files) {//iteracja po lokacjach
                                    try {
                                        locations.add((Location) DataClass.jsonToObject(file));
                                    } catch (BadDataException e) {
                                        e.printStackTrace(pw);
                                        log(sw.toString());
                                    }
                                }
                            }
                            if (value.toString().equals(storypack +"\\keywords")){
                                for (File file:files) {
                                    try {
                                        keywords.add((Keyword) DataClass.jsonToObject(file));
                                    } catch (BadDataException e) {
                                        e.printStackTrace(pw);
                                        log(sw.toString());
                                    }
                                }
                            }
                        }else println("Explain to me: WHY is there  folder, is there's nothing inside it?");
                    }
                }else println(storypack.getName()+"is empty. Unless I'm missing something...");
            }
        }else println("No storypacks avaliable. Too bad!");

        println(locations.toString());
        println(keywords.toString());
    }

    public static void generate(int seed){

    }
    public static void generate(Object seed){
        generate(seed.hashCode());
    }

    @Marker(id="check")
    public static void check(){
        String[] keyword=commands.next().split(" ");
        String command=keyword[0];
        List<String> args=Arrays.asList(keyword);
        args.remove(0);
        for (Keyword key : keywords) {
            if (command.equals(key.getName())){
                try {
                    Method method = Class.forName(key.getMethodName().getClassName()).getMethod(key.getMethodName().getMethodName());
                    method.invoke(Class.forName(key.getMethodName().getClassName()), args.toArray());
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace(pw);
                    log(sw.toString());
                }// TODO: 20.08.2021 end it.
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
                log(sw.toString());
            }
        }
        iterateFiles();
        System.out.println(storyDir.toPath());
        if (locations.size() == 0) {
            println("No locations avaliable. Sorry!");
        }else {
            Location deepForest=locations.get(0);
        }
        println("storypacks = " + Arrays.toString(storypacks));//przeiteruj po tym i znajdź wszystkie foldery i pliki
        println("\n");

    }
}
