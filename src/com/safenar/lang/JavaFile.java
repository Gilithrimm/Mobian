package com.safenar.lang;

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

    public String binNameToPackage(String[] binName){
        StringBuilder builder=new StringBuilder();
        StringBuilder classBuilder=new StringBuilder();
        ArrayList<String> list=new ArrayList<>();
        arg:for (String fragment:binName) {
            for (char first: "qwertyuiopasdfghjklzxcvbnm".toUpperCase().toCharArray()) {//czy zaczyna się wielką literą
                if (fragment.startsWith(String.valueOf(first))){
                    list.add(fragment);
                }else if (list.size()!=0){
                    for (String content:list) {
                        classBuilder.append(content);
                    }
                    break arg;
                }
            }
            builder.append(fragment);
        }
        setClassName(classBuilder.toString());
        return builder.toString();
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
