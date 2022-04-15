package com.loongson.debug;

import com.loongson.debug.resolver.OfflineResolver;
import com.loongson.debug.service.ITbBlockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class ResolverTest {

    @Autowired
    ITbBlockService iTbBlockService;

    @Test
    void ResolverTest1() {
        OfflineResolver offlineResolver = OfflineResolver.getInstance();
        offlineResolver.setiTbBlockService(iTbBlockService);
        File file = new File("data/0414/dump");
        offlineResolver.resolve(file, 5, 8);

    }
}
