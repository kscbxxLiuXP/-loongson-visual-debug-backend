package com.loongson.debug.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileOutputUtil {
    public static BufferedWriter getBufferedWriter(String filepath) throws IOException {
        return new BufferedWriter(new FileWriter(filepath));

    }
}
