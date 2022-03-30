package com.loongson.debug;

import com.loongson.debug.resolver.OfflineResolver;
import org.junit.jupiter.api.Test;

import java.io.File;

public class ResolverTest {


    @Test
    void ResolverTest() {
        OfflineResolver offlineResolver = OfflineResolver.getInstance();
        File file = new File("data/log");
        offlineResolver.resolve(file,5,1);

    }
}
