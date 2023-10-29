package com.etammag.ieat.common.utils;

import java.util.Random;

public class RandomUtil {

    private static final Random random = new Random();
    public static String nextNum(int length) {
        long min = (long) Math.pow(10, length -1);
        long max = min *10 -1;
        long code = random.nextLong(max);
        if(code < min) {
            code += min;
        }
        return String.valueOf(code);
    }

    public static String nextString(int length) {
        return Long.toHexString(random.nextLong()).substring(0, length);
    }

}
