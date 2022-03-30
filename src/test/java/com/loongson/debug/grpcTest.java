package com.loongson.debug;

import com.google.protobuf.Int32Value;
import com.loongson.debug.grpc.Address;
import com.loongson.debug.grpc.DebugServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Test;

public class grpcTest {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50071).usePlaintext().build();
    DebugServiceGrpc.DebugServiceBlockingStub stub = DebugServiceGrpc.newBlockingStub(channel);

    @Test
    void init() {
        stub.init(null);
        stub.init(null);
        stub.init(null);
        stub.init(null);

    }

    @Test
    void simulate(){

    }



    @Test
    void getBreakPoint() {
        Address breakPoint = stub.getBreakPoint(Int32Value.newBuilder().setValue(1).build());
        System.out.println(breakPoint.getAddress());
    }
}
