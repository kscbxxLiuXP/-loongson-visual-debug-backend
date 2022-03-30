package com.loongson.debug;

import org.junit.jupiter.api.Test;

public class TextSplitTest {
    @Test
    void splitTraceTest() {
//        String s = "Trace 0: 0xffd0000540 [00000000/3f78d0b0/0x4000b3] ";
        String s = "Trace 0: 0xffd0005880 [00000000/3f7a79b2/0x4000b3] syscall";

        String[] split = s.split(" ");
        String address = split[2];
        String type = "";
        if (split.length == 5) {
            type = split[4];
        }
        System.out.println(address + " " + type);
    }

    @Test
    void splitTraceTest1() {
        String s = "Trace 0: 0xffd0000540 [00000000/3f78d0b0/0x4000b3] ";
        /*
         * 0:Trace
         * 1:0:
         * 2:0xffd0000540
         * 3:[00000000/3f78d0b0/0x4000b3]
         */
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            System.out.println(i + ":" + s1[i]);
        }
    }

    @Test
    void splitTraceTest2() {
        String s = "Trace 0: 0xffd0005880 [00000000/3f7a79b2/0x4000b3] syscall";
        /*
         * 0:Trace
         * 1:0:
         * 2:0xffd0005880
         * 3:[00000000/3f7a79b2/0x4000b3]
         * 4:syscall
         */
        String[] s1 = s.split(" ");
        for (int i = 0; i < s1.length; i++) {
            System.out.println(i + ":" + s1[i]);
        }
        System.out.println("-----------");
        String[] s2 = s1[3].split("/");
        for (int i = 0; i < s2.length; i++) {
            System.out.println(i + ":" + s2[i]);
        }
        System.out.println("0x"+s2[1]);
    }

    @Test
    void splitTraceLinkingTest() {
        String s = "Linking TBs 0xffd000b180 [3f7a2e08] index 0 -> 0xffd000ae80 [3f7a2e0d]";
        /*
         * 0:Linking
         * 1:TBs
         * 2:0xffd000b180
         * 3:[3f7a2e08]
         * 4:index
         * 5:0
         * 6:->
         * 7:0xffd000ae80
         * 8:[3f7a2e0d]
         */
        String[] s1 = s.split(" ");
        System.out.println(s1[2] + "->" + s1[7]);
    }

    @Test
    void splitRegisterTest() {
        String s = "EAX=00000000 EBX=00000000 ECX=00000000 EDX=00000000";
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            String sub = split[i];//EAX=00000000
            String[] subSplit = sub.split("=");
            System.out.println(i + ":\t[" + subSplit[0] + "]" + subSplit[1]);


        }

    }

    @Test
    void subStringTestTrace(){
        String s1 = "Trace 0: 0xffd009c400 [00000000/3f595000/0x4000b3] ";
        String s2 = "Trace 0: 0xffd0005880 [00000000/3f7a79b2/0x4000b3] syscall";
        System.out.println(s1.length());
        System.out.println(s2.length());
        System.out.println(s2.substring(32,40));
        System.out.println(s2.substring(51));
    }
    @Test
    void subStringTestLinkingTB(){
        String s = "Linking TBs 0xffd000f680 [3f7a328d] index 1 -> 0xffd00a0400 [3f7a32c8]";
        System.out.println(s.substring(26,34));
        System.out.println(s.substring(61,69));
    }
}
