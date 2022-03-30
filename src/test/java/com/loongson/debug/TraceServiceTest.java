package com.loongson.debug;

import com.loongson.debug.entity.Trace;
import com.loongson.debug.service.ITraceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TraceServiceTest {
    @Autowired
    ITraceService traceService;

    @Test
    void traceGetTest() {
        Trace trace = traceService.getById(2);
        System.out.println(trace.getUid() + "," + trace.getLtid());

    }
}
