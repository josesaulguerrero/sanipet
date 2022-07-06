package co.com.sanipet.utils;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleMenu {
    public static String renderOptionsList(String ...options) {
        Arrays.stream(options).forEachOrdered(System.out::println);
        return readLineFromConsole();
    }

    public static String renderQuestion(String question) {
        System.out.println(question);
        return readLineFromConsole();
    }

    private static String readLineFromConsole() {
        return new Scanner(System.in).nextLine();
    }
}
