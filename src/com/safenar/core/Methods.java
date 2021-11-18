package com.safenar.core;

import com.safenar.Functional;
import com.safenar.Main;

public class Methods {
    public static void help() {
        for (Keyword keyword: Main.keywords) {
            Functional.println(keyword.getName()+"-"+keyword.getDescription());
        }
    }
}
