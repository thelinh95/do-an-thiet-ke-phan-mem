package com.baouyen.doan.util;

import java.util.Random;

public class RandomUtil {
    public static int generateRandomNumber(int numDigit) {
        Random random = new Random();
        int min = (int) Math.pow(10, numDigit - 1);
        int max = (int) Math.pow(10, numDigit) - 1;

        return random.nextInt(max - min + 1) + min;
    }

    public static char generateRandomCharacter() {
        int i = new Random().nextInt(26);
        char c = (char) ('a' + i);
        return c;
    }

    public static String generateRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(generateRandomCharacter());
        }
        return sb.toString();
    }
}
