package com.safenar.core;

import com.safenar.Main;

import java.util.ArrayList;

public class JavaFile {
    private String methodName;
    private String className;
    private String packageName;

    public JavaFile(String fullName) {
        String[] split = fullName.split("\\.");
        setMethodName(split[split.length-1].replace("()",""));
        setPackageName(binNameToPackage(split));
    }

    public String binNameToPackage(String[] binName){//bin=binary
        StringBuilder packageBuilder =new StringBuilder();
        StringBuilder classBuilder=new StringBuilder();
        ArrayList<String> classes =new ArrayList<>();
        boolean pain = false;
        arg:for (String fragment:binName) {
            for (char first : Main.ALPHABET) {//czy zaczyna się wielką literą
                if (fragment.startsWith(String.valueOf(first))) {
                    classes.add(fragment);
                    pain=true;
                } else if (classes.size() != 0) {//why elif?
                    for (String content : classes) classBuilder.append(content);
                    break arg;
                }
            }
            if (!pain) packageBuilder.append(fragment).append(".");//HELP ME its just      pain
        }setClassName(classBuilder.toString());
        return packageBuilder.toString();
    }
    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return getPackageName()+"."+getClassName()+"."+getMethodName();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
