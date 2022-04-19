package com.loongson.debug;

import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        System.out.println("0x" + s2[1]);
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
    void subStringTestTrace() {
        String s1 = "Trace 0: 0xffd009c400 [00000000/3f595000/0x4000b3] ";
        String s2 = "Trace 0: 0xffd0005880 [00000000/3f7a79b2/0x4000b3] syscall";
        System.out.println(s1.length());
        System.out.println(s2.length());
        System.out.println(s2.substring(32, 40));
        System.out.println(s2.substring(51));
    }

    @Test
    void subStringTestLinkingTB() {
        String s = "Linking TBs 0xffd000f680 [3f7a328d] index 1 -> 0xffd00a0400 [3f7a32c8]";
        System.out.println(s.substring(26, 34));
        System.out.println(s.substring(61, 69));
    }

    /**
     * 在解析下面的字符串时，有概率与其他输出混淆，导致异常
     * 例：[10, 1] -------   4962282
     * 异常：30.0%] 75/250 frames
     */
    @Test
    void IR1IR2InvokeTextTest() {
        //需要进一步验证
        String line = "[1, 1] -------   4962282";
        String[] s = line.trim().split(",");
        System.out.println(s);
    }

    @Test
    void ProfileSplitText() {
        String line = "gen code size       4988200/536852312";
        String[] split = line.split(" ");
        String[] data = split[split.length - 1].split("/");
        System.out.println(data[0]);
        System.out.println(data[1]);

    }

    @Test
    void TB_avg_host_sizeTextTest() {
        String line = "TB avg host size    134 bytes (expansion ratio: 4.2)";
        line = StringUtils.deleteWhitespace(line);
        String[] split = line.split(":");
        String result1 = StringUtils.getDigits(split[0]);
        System.out.println(result1);
        String result2 = split[1].split("\\)")[0];
        System.out.println(result2);
    }

    @Test
    void cross_page_TB_countTextTest() {
        String line = "cross page TB count 77 (01111%)";
        line = StringUtils.deleteWhitespace(line);
        String[] split = StringUtils.split(line, "(");
        System.out.println(Arrays.toString(split));
        String result1 = StringUtils.getDigits(split[0]);
        System.out.println(result1);
        String result2 = StringUtils.removeEnd(split[1], "%)");
        System.out.println(result2);
    }

    @Test
    void tempTextTest() {
        String line = "direct jump count   8636 (76%) (2 jumps=7108 62%)";
        String result1;
        String result2;
        String result3;
        String result4;
        //[0]direct jump count   8636
        //[1]76.5%)
        //[2]2 jumps=7108 62.6%)
        String[] split1 = line.split("\\(");
        result1 = StringUtils.getDigits(split1[0]);

        result2 = split1[1].split("%")[0];

        //[0]2
        //[1]jumps=7108
        //[2]62.6%)
        String[] split = split1[2].split(" ");

        result3 = StringUtils.getDigits(split[1]);
        result4 = split[2].split("%")[0];
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }

    @Test
    void testTBexecdetailinfo() {
        String line = "0000000000404540  000000000040456a  1           2           11           27           1.45          11       27       2.45";
        String[] split = StringUtils.split(line);
        String remove = "0x" + split[0].replaceAll("^(0+)", "");
        System.out.println(remove);

        System.out.println(Arrays.toString(split));
    }

    @Test
    void temp() {
        String line = "TB hash avg chain   1.018 buckets. Histogram: 1|█▁|2";
        String[] s = StringUtils.split(line);
        String result = s[4];
        System.out.println(result);

    }
}
