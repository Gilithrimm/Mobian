package com.safenar;

import com.safenar.lang.Keyword;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

@Deprecated(since = "0.0.1", forRemoval = true)
public final class DataClass {
    static final HashMap<String, String> map = new HashMap<>();

    private DataClass() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("instantiating static class. bruh");
    }

    public static Object jsonToObject(File from) {
        ArrayList<String> strings = fileToList(from);
        fillMapFromList(strings);
        return fillObject(from);// TODO: 05.07.2021 end me, clear me
    }

    private static void fillMapFromList(ArrayList<String> strings) {
        for (String string : strings) {
            String[] split = string.split(":");
            map.put(split[0], split[1]);
        }
    }

    private static ArrayList<String> fileToList(File from) {
        ArrayList<String> strings=new ArrayList<>();
        try {
            strings = (ArrayList<String>) Files.readAllLines(from.toPath());
        } catch (IOException ioException) {
            Main.logger.log(Main.logger.getStackTrace(ioException));
        }
        return strings;
    }

    private static <T> T fillObject(File from) {
        Object object = new Object();
        if (from.toString().contains("\\keywords")){
            object =new Keyword();
            for (String key : map.keySet()) {
                ((Keyword) object).makeKeyword(map,key);
            }
        }
        map.clear();
        return (T) object;
    }
}
