package com.safenar.lang;

import com.safenar.Main;

public class Methods {

    public static void help() {
        for (Keyword keyword: Main.keywords) {
            Main.println(keyword.getName()+"-"+keyword.getDescription());
        }
    }
}
