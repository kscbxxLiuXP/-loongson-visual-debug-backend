package com.loongson.debug;


import com.loongson.debug.util.RunTimer;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {
    @Test
    void RegExpressionTest() {
        RunTimer runTimer = new RunTimer(true);
        String line = "[100, 1] -------   4962282";
        runTimer.start();
        Pattern pattern = Pattern.compile("^\\[(\\d+), \\d+].*$");
        // 构建模式，其中compile方法还可以传入第二个参数，表示匹配方法，常用的有怱略大小写、匹配多行等。
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            String s = matcher.group(1);
            System.out.println(s);
        }
        runTimer.end("reg");

        runTimer.start();
        String[] split = line.trim().split(",");
        if (split.length==2&& StringUtils.isNumeric(split[0]+split[1])){
            String s = split[0].substring(1);
            System.out.println(s);
        }

        runTimer.end("normal");
    }
}
