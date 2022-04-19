package com.loongson.debug;

import com.loongson.debug.grpc.OnlineDebugServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(DemoApplication.class, args);
//        Server server = ServerBuilder.forPort(50071)
//                .addService(new OnlineDebugServer())
//                .build();
//        server.start();
    }

}
