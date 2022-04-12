package com.loongson.debug;

import com.loongson.debug.entity.DebugTrace;
import com.loongson.debug.service.IDebugtraceService;
import com.loongson.debug.service.ITraceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class saveOrUpdateTest {
    @Autowired
    IDebugtraceService debugtraceService;
    @Test
    void saveOrUpdate(){
        DebugTrace debugTrace = new DebugTrace();

        debugtraceService.saveOrUpdate(debugTrace);
    }
}
