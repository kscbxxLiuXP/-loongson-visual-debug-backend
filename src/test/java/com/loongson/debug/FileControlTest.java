package com.loongson.debug;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.DecimalFormat;

public class FileControlTest {
    static final String basicFilePath = "C:\\Users\\liuxp\\Documents\\毕设\\后端\\demo\\upload";

    @Test
    void getFileSize() {
        File logFile = new File(basicFilePath, "3.log");
        long length = logFile.length();
        System.out.println(renderFileSize(String.valueOf(length)));
    }

    public String renderFileSize(String fileSize) {
        String[] arr = {"Bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};
        float srcsize = Float.parseFloat(fileSize);
        int index = (int) (Math.floor(Math.log(srcsize) / Math.log(1024)));
        double size = srcsize / Math.pow(1024, index);
        size = Double.parseDouble(new DecimalFormat("#.00").format(size));
        return size + arr[index];
    }

}

