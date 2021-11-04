package com.safenar;

import com.safenar.lang.Keyword;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public final class DataClass {
    static final HashMap<String, String> map = new HashMap<>();

    private DataClass() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("instantiating static class. bruh");
    }

    public static Object jsonToObject(File from) throws BadDataException {
        ArrayList<String> strings = fileToList(from);
        fillMapFromList(strings);
        return fillObject(from);// TODO: 05.07.2021 end me, clear me
    }

    private static void fillMapFromList(ArrayList<String> strings) {
        String s= strings.remove(0);
        if (!s.contains("{")) strings.add(0,s);
        s= strings.remove(strings.size()-1);
        if (!s.contains("}")) strings.add(s);
        for (String string : strings) {//rozdzielanie linii
            String replace = string.replaceAll("\"", "");
            if (replace.contains(":")){
                String[] lines = replace.split(":");//rozdzielanie na pary klucz-wartość
                for (int j = 0; j < lines.length; j += 2) {//dodawanie par do mapy
                    String key = lines[j].trim();
                    String value = lines[j+1].replace(",","");
                    map.put(key, value);
                }
            }else {
                //what tf do u expect me to do with "}])" at the end of f..king line?!
            }

        }
    }

    private static ArrayList<String> fileToList(File from) {
        ArrayList<String> strings=new ArrayList<>();
        try {
            strings = (ArrayList<String>) Files.readAllLines(from.toPath());
        } catch (IOException ioException) {
            ioException.printStackTrace(Main.pw);
            Main.logToDebug(Main.sw.toString());
        }
        return strings;
    }

    private static Object fillObject(File from) {
        Object object = new Object();
        if (from.toString().contains("\\keywords")){
            object =new Keyword();
            for (String key : map.keySet()) {
                ((Keyword) object).makeKeyword(map,key);
            }
        }
        map.clear();
        return object;
    }
}
