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
        int i = new Random().nextInt(10);
        char c = (char) ('a' + i);
        return c;
    }
}
