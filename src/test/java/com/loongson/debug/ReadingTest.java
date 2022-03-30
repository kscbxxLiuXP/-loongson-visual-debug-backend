package com.loongson.debug;


import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadingTest {


    @Test
        //静态读取log并解析log内容
    void readingTest() throws Exception {
        Pattern pattern = Pattern.compile("^\\d{1,3}\\.");
        Matcher matcher = null;


        boolean flag = false;
        ArrayList<String> list = new ArrayList<>();

        Resource resource = new ClassPathResource("log");
        InputStream is = resource.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 5 * 1024 * 1024);

        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        int n = 0;
        while ((line = br.readLine()) != null && n < 300) {
            //处理TB块中的内容
            if (line.contains("|| TB translation")) {
                while ((line = br.readLine()) != null) {

                    if (line.contains("\u001B")) {
                        System.out.println(line);
                        System.out.println("contains");
                    }
                    if (!line.contains("tr_fini OK. translation done.")) {
                        stringBuilder.append(line + '\n');

                    } else {
                        list.add(stringBuilder.toString());
                        break;
                    }

                }
            }
            n++;

        }
        for (String s : list) {
            System.out.printf(s);
        }


    }


    int lineNumberCounter(File file) throws Exception {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        reader.skip(Long.MAX_VALUE);
        int lines = reader.getLineNumber();
        reader.close();
        return lines;
    }

    @Test
    void pathTest() {
        File f = new File(this.getClass().getResource("resources").getPath());

        System.out.println(f);
    }

    @Test
    void reading() throws Exception {

        File file = new File("data/part0.txt");

        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 5 * 1024 * 1024);

        String line = "";

        while ((line = br.readLine()) != null) {

            System.out.println(line);
        }
    }

    @Test
        //测试读取大文本数据
    void readingHugeText() throws Exception {
        Resource resource = new ClassPathResource("log");

        long timer = System.currentTimeMillis();
        int bufferSize = 20 * 1024 * 1024;//设读取文件的缓存为20MB
        //建立缓冲文本输入流
        File file = resource.getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        //注意这里有时会乱码，根据自己的文本存储格式，进行调整
        InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream, "utf-8");
        BufferedReader input = new BufferedReader(inputStreamReader, bufferSize);
        //要分割的块数减一,这里表示分割为31个文件
        int splitNum = 30;
        //12046表示我的输入本文的行数，我的文本12046行，由于每行文本较长，所有存储占用较大
        int fileLines = lineNumberCounter(file);
        //分割后存储每个块的行数
        long perSplitLines = fileLines / splitNum;
        for (int i = 0; i <= splitNum; ++i) {
            //分割
            //每个块建立一个输出
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("data/part" + i + ".txt")), "gbk"));
            String line = null;
            //逐行读取，逐行输出
            for (long lineCounter = 0; lineCounter < perSplitLines && (line = input.readLine()) != null; ++lineCounter) {
                output.append(line + "\r");
            }
            output.flush();
            output.close();
            output = null;
        }
        input.close();
        timer = System.currentTimeMillis() - timer;
        //我的1.6g数据不要1分钟，分割完毕
        System.out.println("处理时间：" + timer);

    }

    @Test
    void BufferedWriterTest() throws Exception {
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("test.txt"))));
        output.append("123456\n");
        output.append("123456\r");
        output.append("123456\n");
        output.append("123456\r");
        output.flush();
        output.close();
        output = null;
    }
}
