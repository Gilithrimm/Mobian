package old.safenar.lang;

import old.safenar.Main;

public class Methods {
    public static void help() {
        for (Keyword keyword: old.safenar.Main.keywords) {
            Main.logger.println(keyword.getName()+"-"+keyword.getDescription());
        }
    }
}
