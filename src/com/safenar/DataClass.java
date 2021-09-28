package com.safenar;

import com.safenar.lang.JavaFile;
import com.safenar.lang.Keyword;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

public class DataClass {
    static final HashMap<String, String> map = new HashMap<>();
    public static Object jsonToObject(File from) throws BadDataException {
        Object object = new Object();
        ArrayList<String> strings = new ArrayList<>();
        try {
            strings = (ArrayList<String>) Files.readAllLines(from.toPath());
        } catch (IOException ioException) {
            ioException.printStackTrace(Main.pw);
            Main.log(Main.debugLog.toPath(),Main.sw.toString());
        }
        String s=strings.remove(0);
        if (!s.contains("{")) strings.add(0,s);
        s=strings.remove(strings.size()-1);
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

            }

        }
        for (File storypack:Main.storypacks) {
            if (from.toString().contains(storypack +"\\keywords")){
                object=new Keyword();
                keyword:for (String key:map.keySet()) {
                    switch (key.toLowerCase()) {
                        case "name" -> ((Keyword) object).setName(map.get(key));
                        case "desc" -> ((Keyword) object).setDescription(map.get(key));
                        case "methodname" -> ((Keyword) object).setMethodName(new JavaFile(map.get(key)));
                    }
                }
            }
            map.clear();
        }
        return object;// TODO: 05.07.2021 END ME!!!!!!
    }
}
