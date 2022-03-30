package com.loongson.debug.resolver;

public class ColorHandler {


    public String handle(String line) {
        if (!line.contains("\u001B")) {
            return line;
        }
        String s = line.replaceAll("\\u001B\\[[0-9][0-9][m]", "");
        String replace = s.replace("\u001B[m", "");
        return replace;

    }
}
