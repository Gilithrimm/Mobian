package com.safenar.lang;

import java.util.ArrayList;

public class JavaFile {
    private String methodName;
    private String className;
    private String packageName;

    public JavaFile(String fullName) {
        String[] binName = fullName.split("\\.");
        this.packageName = binNameToPackage(binName);
        this.className = binName[binName.length-2];
        this.methodName = binName[binName.length-1];
    }

    public String binNameToPackage(String[] binName){
        ArrayList<String> packageName = new ArrayList<>();
        for(int i = 0; i < binName.length-1; i++){
            packageName.add(binName[i]);
            //what if at the end of the array there is a method name?
            //if so, we need to remove it from the package name
            if(binName[i+1].contains("(")){
                break;
            }
        }
        return String.join(".", packageName);
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

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public String toString() {
        return getPackageName()+"."+getClassName()+"."+getMethodName();
    }
}
