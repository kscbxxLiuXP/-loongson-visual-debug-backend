package com.loongson.debug.resolver;

import com.alibaba.fastjson.JSON;
import com.loongson.debug.entity.Head;

import java.io.*;
import java.util.ArrayList;

public class HeadHandler {
    private ColorHandler colorHandler;
    private boolean colorCorrection;

    public HeadHandler() {
        this.colorHandler = new ColorHandler();
        this.colorCorrection = true;
    }

    public void setColorCorrection(boolean colorCorrection) {
        this.colorCorrection = colorCorrection;
    }

    public HeadHandler(boolean colorCorrection) {
        this.colorCorrection = colorCorrection;
    }

    public void handle(BufferedReader br) throws Exception {

        String line = "";
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("splitData/head.txt"))));

        while (!(line = br.readLine()).startsWith("[fpu rotate] native jump")) {
            if (colorCorrection)
                line = colorHandler.handle(line);
            output.append(line + "\n");

        }
        output.append(line);
        output.flush();
        output.close();
        output = null;
    }

    public Head handleR(BufferedReader br) throws Exception {

        String line = "";
        ArrayList<String> list = new ArrayList<>();
        while (!(line = br.readLine()).startsWith("[fpu rotate] native jump")) {
            if (colorCorrection)
                line = colorHandler.handle(line);
            list.add(line);

        }
        Head head = new Head();
        head.setHeadtext(JSON.toJSONString(list));
        return head;
    }

}
