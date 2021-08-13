package com.safenar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LogTest {
    private static void writeToFile(Object obj) throws IOException {
        //Retrieving the log file
        Path logFile = Paths.get("ExceptionLog.txt");
        //Preparing the data to be logged
        byte[] bytes = (LocalDateTime.now()+": "+ obj.toString()+"\n").getBytes();
        //Appending the exception to your file
        if (!logFile.toFile().exists()) Files.createFile(logFile);
        Files.write(logFile, bytes, StandardOpenOption.APPEND);
        System.out.println("Exception logged to your file");
    }
    public static void main(String... args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = {10, 20, 30, 2, 0, 8};
        System.out.println("Array: "+ Arrays.toString(arr));
        System.out.println("Choose numerator and denominator (not 0) from this array (enter positions 0 to 5)");
        try {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int result = (arr[a])/(arr[b]);
            System.out.println("Result of "+arr[a]+"/"+arr[b]+": "+result);
        }catch(ArrayIndexOutOfBoundsException ex) {
            System.err.println("Warning: You have chosen a position which is not in the array");
            ex.printStackTrace();
        }catch(ArithmeticException ex) {
            System.err.println("Warning: You cannot divide an number with 0");
            ex.printStackTrace();
        }catch(InputMismatchException ex) {
            System.err.println("Warning: You have entered invalid input");
            ex.printStackTrace();
        }
    }
}
