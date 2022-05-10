package com.safenar.lang;

import java.util.Map;

public class Keyword{
    private String name;
    private String description;
    private JavaFile methodName;

    public Keyword() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JavaFile getMethodName() {
        return methodName;
    }

    public void setMethodName(JavaFile methodName) {
        this.methodName = methodName;
    }

    public void makeKeyword(Map<String ,String> map, String key) {
        switch (key.toLowerCase()) {
            case "name" -> setName(map.get(key));
            case "desc" -> setDescription(map.get(key));
            case "methodname" -> setMethodName(new JavaFile(map.get(key)));
        }
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
