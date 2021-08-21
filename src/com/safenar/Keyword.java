package com.safenar;

import java.util.Arrays;
import java.util.List;

public class Keyword{
    private String name;
    private String description;

    private JavaFile methodName;
    private Keyword alt;

    public Keyword() {}

    public Keyword(String name, String description, JavaFile methodName) {
        this.name = name;
        this.description = description;
        this.methodName = methodName;
    }

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

    public Keyword getAlt() {
        return alt;
    }

    public void setAlt(Keyword alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "Keyword{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", methodName='" + methodName + '\'' +
                ", alt=" + alt +
                '}';
    }
}
