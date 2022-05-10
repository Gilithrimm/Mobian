package com.safenar.util;

import com.safenar.Logger;
import com.safenar.Main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JSONParser<T> {
    private final List<String> json;
    private final Logger logger=Main.logger;
    private T instance;

    public JSONParser(String json,Class<T> object){
        this.json = Arrays.stream(json.split("\n")).toList();
        try {
            instance = Main.printlnAndReturn(
                    object.getDeclaredConstructor().newInstance(),"instance: ");
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            logger.log(logger.getStackTrace(e));
        }
    }

    public JSONParser(File file,Class<T> o) throws IOException {
        this(Files.readAllLines(file.toPath()).toString(),o);
    }

    public JSONParser<T> parse() {
        for (String line : json) {
            String[] keyValue = Main.printlnAndReturn(line.trim().split(":"), "keyValue: ");
            if (keyValue.length == 2) {
                Field field = getField(keyValue[0]);
                if (field != null) {
                    try {
                        field.setAccessible(true);
                        setValue(field,instance,keyValue[1]);
                    } catch (IllegalAccessException e) {
                        logger.log(logger.getStackTrace(e));
                    }
                }
            }
        }
        return this;
    }

    private void setValue(Field field, T instance, Object value) throws IllegalAccessException {
        if (field.getType().getTypeName().equals("int")) {
            value=
                    Integer.valueOf(
                            String.valueOf(value)
                                    .replace(":,/\\|[{}]",""));
        }
        field.set(instance,value);
    }

    private Field getField(String key) {
        Field[] fields = instance.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (key.contains(field.getName())) {
                return field;
            }
        }
        return null;
    }

    public T getAsJsonObject() {
        return Main.printlnAndReturn(instance,"instance: ");
    }

}
