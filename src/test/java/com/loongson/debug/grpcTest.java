package com.loongson.debug;

import com.google.protobuf.Int32Value;
import com.loongson.debug.grpc.Address;
import com.loongson.debug.grpc.DebugServiceGrpc;
import com.loongson.debug.grpc.DebugState;
import com.loongson.debug.grpc.IPAddress;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.Test;

public class grpcTest {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50071).usePlaintext().build();
    DebugServiceGrpc.DebugServiceBlockingStub stub = DebugServiceGrpc.newBlockingStub(channel);

    @Test
    void init() {
        IPAddress ipAddress1 = IPAddress.newBuilder().setIp("17.5.6.3").build();
        IPAddress ipAddress2 = IPAddress.newBuilder().setIp("10.2.5.5").build();
        IPAddress ipAddress3 = IPAddress.newBuilder().setIp("192.168.0.1").build();
        IPAddress ipAddress4 = IPAddress.newBuilder().setIp("Unknow").build();
        stub.init(ipAddress1);
        stub.init(ipAddress2);
        stub.init(ipAddress3);
        stub.init(ipAddress4);

    }

    @Test
    void simulate(){

    }
    @Test
    void setState(){
        stub.setDebugState(DebugState.newBuilder().setId(1).setState(2).build());
    }


    @Test
    void getBreakPoint() {
        Address breakPoint = stub.getBreakPoint(Int32Value.newBuilder().setValue(1).build());
        System.out.println(breakPoint.getAddress());
    }
}
