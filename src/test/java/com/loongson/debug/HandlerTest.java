package com.loongson.debug;


import com.loongson.debug.dto.TraceDTO;
import com.loongson.debug.entity.IR1;
import com.loongson.debug.entity.IR2;
import com.loongson.debug.dto.TBBlockDTO;
import com.loongson.debug.entity.LtlogAnalysis;
import com.loongson.debug.entity.TbBlock;
import com.loongson.debug.resolver.*;
import com.loongson.debug.service.ITbBlockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
public class HandlerTest {
    @Autowired
    ITbBlockService tbBlockService;

    IR1Handler ir1Handler = new IR1Handler();

    IR2Handler ir2Handler = new IR2Handler();

    TBHandler tbHandler = new TBHandler();

    TraceHandler traceHandler = new TraceHandler();

    ProfileHandler profileHandler = new ProfileHandler();

    @Test
    void IR1HandlerTest() {
        IR1 handle = ir1Handler.handle("IR1[0] 0x3f78d0b0:\tmov\t\teax, esp");
        IR1 handle2 = ir1Handler.handle("IR1[6] 0x3f7923f0:\tret\t");
        System.out.println(handle);
        System.out.println(handle2);

    }

    @Test
    void IR2HandlerTest() {
        IR2 handle = ir2Handler.handle("IR2[022] at 0xffe8000584 0x16001ff6 pcaddi    $fp,255");
        IR2 handle1 = ir2Handler.handle("IR2[023] at 0xffe8000588 0x147ef1b5 lu32i.d   $x,0x3f78d");
        IR2 handle2 = ir2Handler.handle("IR2[011] at 0xffe800002c 0x29c1e061 ld.bu     \u001B[32mitmp9\u001B[m,$sp,120");
        System.out.println(handle);
        System.out.println(handle1);
        System.out.println(handle2);
    }

    @Test
    void ColorHandlerTest() {
        ColorHandler colorHandler = new ColorHandler();
        String handle = colorHandler.handle("IR2[011] at 0xffe800002c 0x29c1e061 ld.bu     \u001B[32mitmp9\u001B[m,$sp,120");
        String handle2 = colorHandler.handle("IR2[001] at 0xffe8000160 0x01149809 movgr2fr.w  \u001B[34mftmp0,$fa0");

        System.out.println(handle);
        System.out.println(handle2);

    }

    @Test
    void TBBlockConvertTest() {
        File file = new File("splitData/TB0.txt");
        TBBlockDTO tbBlockDto = tbHandler.convert(file, 1);
        System.out.println(tbBlockDto.toString());
    }

    @Test
    void TBHnadlerTest() throws Exception {
        File file = new File("splitData/TB1");
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 5 * 1024 * 1024);
        TbBlock tbBlockDto = tbHandler.handleT(br, 1, 1,new HashMap<>());
        System.out.println(tbBlockDto);

    }

    @Test
    void TraceHandlerTest() throws Exception {
        traceHandler.init(tbBlockService);

        File file = new File("data/tracefull0326");
        TraceDTO handle = traceHandler.handle(file, 4);
        System.out.println(handle);
    }

    @Test
    void OnlineTraceHandlerTest() {
        //register:ffffffff9000010b 0000000a 3f7b4f3c 6fffffff 3ffff360 3ffff498 6ffffdff 3f7b5000
        //result:18446744071830503691,10,1065045820,1879048191,1073738592,1073738904,1879047679,1065046016
        String s = "18446744071830503691,10,1065045820,1879048191,1073738592,1073738904,1879047679,1065046016";
        String[] split = s.split(",");
        for (String s1 : split) {
            System.out.println(Long.parseLong(s1,16));
        }

    }

    @Test
    void ProfileHnadlerTest() throws Exception {
        File file = new File("data/0414/profile.log");
        InputStream is = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr, 5 * 1024 * 1024);
        ArrayList<String> strings = profileHandler.handleT(br, 8);
        System.out.println(strings);

    }

}
