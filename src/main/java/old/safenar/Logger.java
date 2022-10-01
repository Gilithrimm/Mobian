package old.safenar;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    Path logFile;
    final StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    public Logger(Path logFile) {
        this.logFile = logFile;
    }

    public void log(Object logs){
        try {
            byte[] bytes = (LocalDateTime
                        .now()
                        .format(DateTimeFormatter
                                .ofPattern("yyyy-MM-dd HH:mm:ss"))
                    + " " + logs.toString() + "\n").getBytes();
            if (!logFile.toFile().exists()) Files.createFile(logFile);
            Files.write(logFile, bytes, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStackTrace(Exception e){
        e.printStackTrace(pw);
        return sw.toString();
    }

    public void println(Object word){
        System.out.println(word);
        log(word);
    }
}
