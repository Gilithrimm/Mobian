package com.safenar.lang;

public class JavaFile {
    private String methodName;
    private String className;

    public JavaFile(String fullName) {
        String[] split = fullName.split("\\.");
        setMethodName(split[split.length-1].replace("()",""));
        String binName="";
        StringBuilder sb=new StringBuilder(binName);
        for (String binClass:split) {
            if (binClass.endsWith("()")&&binClass.replace("()","").equals(getMethodName())) break;
            if (binClass.equalsIgnoreCase(getMethodName())) break;
            sb.append(binClass);
            sb.append(".");
        }
        binName=sb.toString();
        if (binName.endsWith(".")) setClassName(binName.substring(0,binName.length()-1));
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