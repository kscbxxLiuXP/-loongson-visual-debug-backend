package com.loongson.debug.controller;

import com.loongson.debug.grpc.OnlineDebugServer;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class ServiceController {

    private Server server;


    @GetMapping("/rpc/start")
    public String startServer() throws IOException {
        if (server == null) {
            server = ServerBuilder.forPort(50071)
                    .addService(new OnlineDebugServer())
                    .build();
            server.start();
            return "Server Start";
        }
        return "Server already started!";


    }

    @GetMapping("/rpc/stop")
    public String stopServer() {
        server.shutdownNow();
        server = null;
        return "Server Stop";
    }

    @GetMapping("/rpc/status")
    public String serverStatus() {
        if (server == null) {
            return "server not start";
        } else {
            return "Server running!";
        }


    }
}
