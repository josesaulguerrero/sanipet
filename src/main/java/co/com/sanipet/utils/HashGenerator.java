package co.com.sanipet.utils;

import java.util.Random;

public class HashGenerator {
    public static String generateRandomAlphanumericString(int length) {
        String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(ABC.charAt(new Random().nextInt(ABC.length())));
        }
        return sb.toString();
    }
}
