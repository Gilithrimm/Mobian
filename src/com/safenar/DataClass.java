package com.safenar;

import com.safenar.data.Location;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

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
            if (from.toString().contains(storypack.toString()+"\\locations")){
                object=new Location();
                location:for (String key:map.keySet()) {
                    switch (key){
                        case "name":
                            ((Location) object).setName(map.get(key));
                            break;
                        case "desc":
                            ((Location)object).setDescription(map.get(key));
                            break;
                        case "type":
                            ((Location)object).setType(map.get(key));
                            break;
                        case "id":
                            ((Location)object).setId(map.get(key));
                        default:
                            throw new BadDataException();
                    }
                    if (((Location) object).getId() == null) {
                        if (((Location) object).getName() == null) {
                            throw new BadDataException("There is no id!");
                        }
                        else {
                            String id=storypack.toString().replace(Main.storyDir.toString(),"").replace("\\", "")+":"+((Location) object).getName();
                            ((Location) object).setId(id.toLowerCase().replaceAll(" ","_"));
                        }
                    }
                }
            }
            if (from.toString().contains(storypack.toString()+"\\keywords")){
                object=new Keyword();
                keyword:for (String key:map.keySet()) {
                    switch (key.toLowerCase()){
                        case "name":
                            ((Keyword) object).setName(map.get(key));
                            break;
                        case "desc":
                            ((Keyword)object).setDescription(map.get(key));
                            break;
                        case "methodname":
                            ((Keyword) object).setMethodName(new JavaFile(map.get(key)));
                            break;
                        case "args":
                            List<String> args=Arrays.asList(map.get(key));
//                            Main.println(args);
//                            ((Keyword) object).setArgs((Argument[]) args.toArray());

                    }
                }
            }
            map.clear();
        }
        return object;// TODO: 05.07.2021 END ME!!!!!!
    }

    public static Object jsonToObject(String from,Object cast) throws BadDataException {
        ArrayList<String> strings= (ArrayList<String>) Arrays.asList(from.split("\n"));
        String s=strings.remove(0);
        if (!s.equals("{")) strings.add(0,s);
        s=strings.remove(strings.size()-1);
        if (!s.equals("}")) strings.add(s);
        for (String string : strings) {//rozdzielanie linii
            String replace = string.replaceAll("\"", "");
            String[] lines = replace.split(":");//rozdzielanie na pary klucz-wartość
            for (int j = 0; j < lines.length; j = j + 2) {//dodawanie par do mapy
                String key = lines[j].trim();
                String value = lines[j + 1].replace(",","");
                map.put(key, value);
            }
        }
        if (cast instanceof Location){
            for (String key:map.keySet()) {
                switch (key){
                    case "name":
                        ((Location) cast).setName(map.get(key));
                        break;
                    case "desc":
                        ((Location) cast).setDescription(map.get(key));
                        break;
                    case "type":
                        ((Location) cast).setType(map.get(key));
                        break;
                    case "id":
                        ((Location) cast).setId(map.get(key));
                    default:
                        throw new BadDataException();
                }
            }
            if (((Location) cast).getId() == null) {
                throw new BadDataException("There is no id!");
            }
        }
        if (cast instanceof Argument){
            for (String key : map.keySet()) {
                switch (key){
                    case "type":

                        break;
                    case "defaults":
//                        ((Argument) cast).setDefaults(Collections.singletonList(map.get(key)));
                        break;
                    case "isRequired":
                        ((Argument) cast).setRequired(Boolean.parseBoolean(map.get(key)));
                        break;
                }
            }
        }
        else throw new ClassCastException("No class like that is allowed/checked here");//it's 11pm and i don't wanna go sleep yet, rip any thinking rn
        // TODO: 17.07.2021 FIX THIS SHIT!
        return cast;
    }
    /*public static List<HashMap<String,String>> jsonArrayToList(String array){
        ArrayList<String> strings= (ArrayList<String>) Arrays.asList(array.split(","));
        String remove = strings.get(0).replace("[","");
        if (!remove.equals("["))strings.add(0,remove);
        remove=strings.remove(strings.size()-1);
        if (!remove.equals("]"))strings.add(remove);
        for (String string : strings) {//rozdzielanie linii
            String replace = string.replaceAll("\"", "");
            replace=replace.replace("{","");
            replace=replace.replace("}","");
            String[] lines = replace.split(":");//rozdzielanie na pary klucz-wartość
            for (int j = 0; j < lines.length; j = j + 2) {//dodawanie par do mapy
                String key = lines[j].trim();
                String value = lines[j + 1].replace(",","");
                map.put(key, value);
            }
        }
        return strings;
    }*/

}
