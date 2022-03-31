package com.loongson.debug.grpc;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.loongson.debug.helper.DebugVar;
import com.loongson.debug.helper.GlobalDebugMaintainer;
import com.loongson.debug.websocket.WebSocket;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OnlineDebugServer extends DebugServiceGrpc.DebugServiceImplBase {
    WebSocket webSocket = new WebSocket();

    private GlobalDebugMaintainer globalDebugMaintainer;

    public OnlineDebugServer() {
        globalDebugMaintainer = GlobalDebugMaintainer.getInstance();
    }

    @Override
    public void setDEBUGTrue(Int32Value request, StreamObserver<Empty> responseObserver) {
        int id = request.getValue();
        globalDebugMaintainer.get(id).setDEBUG(true);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void setCanExecuteFalse(Int32Value request, StreamObserver<Empty> responseObserver) {
        int id = request.getValue();
        globalDebugMaintainer.get(id).setCanExecute(false);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getCanStart(Int32Value request, StreamObserver<CanStart> responseObserver) {
        int id = request.getValue();
        boolean canStart = globalDebugMaintainer.get(id).isCanStart();

        responseObserver.onNext(CanStart.newBuilder().setCanStart(canStart).build());
        responseObserver.onCompleted();
    }

    @Override
    public void synchronizeVar(Int32Value request, StreamObserver<SynchronizeVarReply> responseObserver) {
        int id = request.getValue();
        DebugVar debugVar = globalDebugMaintainer.get(id);

        responseObserver.onNext(SynchronizeVarReply
                .newBuilder()
                .setCanExecute(debugVar.isCanExecute())
                .setDEBUG(debugVar.isDEBUG())
                .setBreakPointAddress(debugVar.getBreakPointAddress())
                .build());
        responseObserver.onCompleted();

    }

    @Override
    public void executeEnd(Int32Value request, StreamObserver<Empty> responseObserver) {
        int id = request.getValue();
        globalDebugMaintainer.get(id).setEnd(true);
        if (globalDebugMaintainer.get(id).getDebugState()!=5){
            globalDebugMaintainer.get(id).setDebugState(4);
            JSONObject reply = new JSONObject();
            reply.put("type", 7);
            reply.put("data", globalDebugMaintainer.get(id));
            try {
                webSocket.sendMessageTo(reply.toJSONString(), id);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void init(IPAddress request, StreamObserver<Int32Value> responseObserver) {
        int id = globalDebugMaintainer.create(request.getIp());
        responseObserver.onNext(Int32Value.newBuilder().setValue(id).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getBreakPoint(Int32Value request, StreamObserver<Address> responseObserver) {
        int id = request.getValue();
        long breakPointAddress = globalDebugMaintainer.get(id).getBreakPointAddress();

        responseObserver.onNext(Address.newBuilder().setAddress(breakPointAddress).build());
        responseObserver.onCompleted();
    }

    @Override
    public void setCurrentAddress(Address request, StreamObserver<Empty> responseObserver) {
        int id = request.getId();
        globalDebugMaintainer.get(id).setCurrentAddress(request.getAddress());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getCanExecute(Int32Value request, StreamObserver<CanExecute> responseObserver) {
        boolean canExecute = globalDebugMaintainer.get(request.getValue()).isCanExecute();
        responseObserver.onNext(CanExecute.newBuilder().setCanExecute(canExecute).build());
        responseObserver.onCompleted();
    }

    @Override
    public void setDebugState(DebugState request, StreamObserver<Empty> responseObserver) {
        System.out.println("Server: setDebugState");
        globalDebugMaintainer.get(request.getId()).setDebugState(request.getState());
        JSONObject reply = new JSONObject();
        reply.put("type", 6);
        reply.put("data", globalDebugMaintainer.get(request.getId()));
        try {
            webSocket.sendMessageTo(reply.toJSONString(), request.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();

    }

    @Override
    public void sendTrace(Trace request, StreamObserver<Empty> responseObserver) {
        System.out.println("Server sendTrace Called");
        System.out.println(request.getTrace());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
