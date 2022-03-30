package com.loongson.debug;

import com.loongson.debug.helper.DebugVar;
import com.loongson.debug.helper.GlobalDebugMaintainer;
import org.junit.jupiter.api.Test;

public class GlobalDebugMaintainerTest {


    @Test
    void testConcurrence() {
        GlobalDebugMaintainer maintainer = GlobalDebugMaintainer.getInstance();
        maintainer.create("");//1
        maintainer.create("");//2
        maintainer.create("");//3


        DebugVar debugVar1 = maintainer.get(1);
        debugVar1.setBreakPointAddress(123456);

        DebugVar debugVar3 = maintainer.get(3);
        debugVar3.setBreakPointAddress(123456);

        GlobalDebugMaintainer maintainer2 = GlobalDebugMaintainer.getInstance();
        maintainer2.create("");//4
        long breakPointAddress = maintainer2.get(1).getBreakPointAddress();
        System.out.println(breakPointAddress);
        maintainer2.remove(2);
        maintainer.create("");
        maintainer.get(4).setDebugState(1515);
        System.out.println(maintainer.getAll());
        System.out.println(maintainer2.getAll());
    }
    @Test
    void get(){
        GlobalDebugMaintainer maintainer = GlobalDebugMaintainer.getInstance();
        System.out.println(maintainer.getAll());
    }
}
