package com.loongson.debug;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class hexDecTest {
    @Test
    void hexToDec() {
        int a[] = new int[]{1, 2};

        List<Integer> addresses = Arrays.asList(1, 2);


        int i = Integer.parseInt((String) "3f78dd90", (int) 16);
        System.out.println(i);
    }

    static class Address {
        long pc;
        boolean link;

    }

    @Test
    void registerConvertTest(){
        //register:ffffffff9000010b 0000000a 3f7b4f3c 6fffffff 3ffff360 3ffff498 6ffffdff 3f7b5000
        //result:18446744071830503691,10,1065045820,1879048191,1073738592,1073738904,1879047679,1065046016
        String s = "1064885760,10,1065045820,1879048191,1073738592,1073738904,1879047679,1065046016";
        String[] split = s.split(",");
        for (String s1 : split) {
            System.out.println(Long.parseLong(s1));
        }
    }
}
