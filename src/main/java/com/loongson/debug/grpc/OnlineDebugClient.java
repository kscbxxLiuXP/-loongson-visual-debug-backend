package com.loongson.debug.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class OnlineDebugClient {
    DebugServiceGrpc.DebugServiceBlockingStub stub;
    ManagedChannel channel;

    public static void main(String[] args) {

        OnlineDebugClient client = new OnlineDebugClient();
        Int32Value init = client.stub.init(null);
        System.out.println(init);
//        Address breakPoint = client.stub.getBreakPoint(Int32Value.newBuilder().setValue(15).build());
//        System.out.println(breakPoint.getAddress());

    }

    public OnlineDebugClient() {
        channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", 50071)
                .usePlaintext()
                .build();
        stub = DebugServiceGrpc.newBlockingStub(channel);
    }
}
