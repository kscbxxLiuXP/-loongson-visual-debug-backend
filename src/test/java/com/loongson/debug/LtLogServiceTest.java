package com.loongson.debug;

import com.loongson.debug.entity.LtLog;
import com.loongson.debug.service.ILtLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LtLogServiceTest {

    @Autowired
    ILtLogService ltLogService;

    @Test
    void testLtLogUpdate() {
        LtLog ltLog = new LtLog();
        ltLog.setLine(123456);
        ltLog.setSize(156151L);
        ltLog.setUploadtime("2020-25-5 14:22:22");
        ltLog.setUserid(123);
        ltLog.setFilename("static");
        ltLogService.createNewLog(ltLog);
        ltLog.setHeadid(12);
        ltLogService.updateLog(ltLog);
    }
}
