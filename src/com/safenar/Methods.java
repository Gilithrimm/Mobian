package com.safenar;

public class Methods {

    private static String print;

    static public String getPrint() {
        return print;
    }
    static public void setPrint(String print) {
        Methods.print = print;
    }

    public static void help() {
        for (Keyword keyword:Main.keywords) {
            Main.println(keyword.getName()+"-"+keyword.getDescription());
        }
    }
}
