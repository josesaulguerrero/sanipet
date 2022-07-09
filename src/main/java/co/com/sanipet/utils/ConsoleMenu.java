package co.com.sanipet.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class ConsoleMenu {
    public static String renderAndRead(String ...options) {
        Arrays.stream(options).forEachOrdered(System.out::println);
        return readLineFromConsole();
    }

    public static String renderAndRead(String question) {
        System.out.println(question);
        return readLineFromConsole();
    }

    public static String renderAndVerify(Predicate<String> validator, String ...options) {
        Optional<String> option = Optional.of("");
        while(!validator.test(option.get())) {
            option = Optional.of(renderAndRead(options));
        }
        return option.get();
    }

    public static String renderAndVerify(Predicate<String> validator, String question) {
        Optional<String> answer = Optional.of("");
        while(!validator.test(answer.get())) {
            answer = Optional.of(renderAndRead(question));
        }
        return answer.get();
    }

    public static LocalDate renderAndVerifyDate(String question) {
        String stringifiedDate = renderAndVerify(
                DateUtils::isValidDate, // method referencing
                question
        );
        return LocalDate.parse(stringifiedDate);
    }

    private static String readLineFromConsole() {
        return new Scanner(System.in).nextLine();
    }
}
