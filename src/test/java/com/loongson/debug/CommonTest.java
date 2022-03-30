package com.loongson.debug;


import org.junit.jupiter.api.Test;

public class CommonTest {
@Test
void HexCompareTest(){

    String a = "0xffe8000574";
    String b = "0xffe8000574";
    long l = Long.parseLong(a.substring(2),16);
    long l2 = Long.parseLong(b.substring(2),16);
    System.out.println(l<=l2);

}
}
