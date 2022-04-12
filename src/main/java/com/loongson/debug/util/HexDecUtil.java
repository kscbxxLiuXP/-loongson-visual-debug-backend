package com.loongson.debug.util;

public class HexDecUtil {
    public static long hexToDec(String hex) {
        long i = Long.parseLong(hex, (int) 16);
        return i;
    }

    public static String decToHex(long dec) {
        String s = Long.toHexString(dec);
        return s;
    }
}
