package com.safenar;

public class JavaFile {
    private String methodName;
    private String className;

    public JavaFile(String methodName, String className) {
        this.methodName = methodName;
        this.className = className;
    }

    public JavaFile(String methodName) {
        methodName.split("\\.");// TODO: 20.08.2021 end it.
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
        return getClassName()+"."+getMethodName();
    }
}
