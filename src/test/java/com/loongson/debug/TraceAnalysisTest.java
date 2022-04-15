package com.loongson.debug;

import org.junit.jupiter.api.Test;

import java.io.*;

public class TraceAnalysisTest {
    @Test
    void test() throws IOException {
        File file = new File("data/tracefull0326");
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 5 * 1024 * 1024);
        String line = "";
        while ((line = br.readLine()) != null) {
            if (line.startsWith(""))
            System.out.println(line);
        }
    }
}
