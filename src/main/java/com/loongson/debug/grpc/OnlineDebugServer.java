package com.loongson.debug.grpc;

import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.loongson.debug.entity.OnlineDebug;
import com.loongson.debug.helper.GlobalDebugMaintainer;
import com.loongson.debug.resolver.OnlineTraceHandler;
import com.loongson.debug.websocket.WebSocket;
import io.grpc.stub.StreamObserver;
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
        globalDebugMaintainer.setDEBUG(id, true);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void setCanExecuteFalse(Int32Value request, StreamObserver<Empty> responseObserver) {
        int id = request.getValue();
        globalDebugMaintainer.setCanExecute(id, false);
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getCanStart(Int32Value request, StreamObserver<CanStart> responseObserver) {
        int id = request.getValue();
        boolean canStart = globalDebugMaintainer.get(id).getCanstart();

        responseObserver.onNext(CanStart.newBuilder().setCanStart(canStart).build());
        responseObserver.onCompleted();
    }

    @Override
    public void synchronizeVar(Int32Value request, StreamObserver<SynchronizeVarReply> responseObserver) {
        int id = request.getValue();
        OnlineDebug onlineDebug = globalDebugMaintainer.get(id);

        responseObserver.onNext(SynchronizeVarReply.newBuilder().setCanExecute(onlineDebug.getCanexecute()).setDEBUG(onlineDebug.getDebug()).setBreakPointAddress(onlineDebug.getBreakpointaddress()).build());
        responseObserver.onCompleted();

    }

    @Override
    public void executeEnd(Int32Value request, StreamObserver<Empty> responseObserver) {
        int id = request.getValue();
        globalDebugMaintainer.setEnd(id, true);
        if (globalDebugMaintainer.get(id).getDebugstate() != 5) {
            globalDebugMaintainer.setDebugState(id, 4);
            JSONObject reply = new JSONObject();
            reply.put("type", 7);
            reply.put("data", globalDebugMaintainer.get(id));
            try {
                webSocket.sendMessageTo(reply.toJSONString(), id);
            } catch (Exception e) {
                e.printStackTrace();
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
        long breakPointAddress = globalDebugMaintainer.get(id).getBreakpointaddress();

        responseObserver.onNext(Address.newBuilder().setAddress(breakPointAddress).build());
        responseObserver.onCompleted();
    }

    @Override
    public void setCurrentAddress(Address request, StreamObserver<Empty> responseObserver) {
        int id = request.getId();
        globalDebugMaintainer.setCurrentAddress(id, request.getAddress());
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getCanExecute(Int32Value request, StreamObserver<CanExecute> responseObserver) {
        boolean canExecute = globalDebugMaintainer.get(request.getValue()).getCanexecute();
        responseObserver.onNext(CanExecute.newBuilder().setCanExecute(canExecute).build());
        responseObserver.onCompleted();
    }

    @Override
    public void setDebugState(DebugState request, StreamObserver<Empty> responseObserver) {
        System.out.println("Server: setDebugState");
        globalDebugMaintainer.setDebugState(request.getId(), request.getState());
        JSONObject reply = new JSONObject();
        reply.put("type", 6);
        reply.put("data", globalDebugMaintainer.get(request.getId()));
        try {
            webSocket.sendMessageTo(reply.toJSONString(), request.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();

    }

    @Override
    public void sendTrace(Trace request, StreamObserver<Empty> responseObserver) {
        System.out.println("Server sendTrace Called");

        OnlineTraceHandler traceHandler = new OnlineTraceHandler();
        traceHandler.handleOnlineTrace(request.getId(), request.getAddress(), request.getTbtype(),request.getRegisters());
        System.out.println(request.getAddress());
        System.out.println(request.getRegisters());

        JSONObject reply = new JSONObject();

        reply.put("type", 8);
        reply.put("trace", request.getAddress());
        reply.put("tbtype", request.getTbtype());
        reply.put("registers", request.getRegisters());
        reply.put("data", globalDebugMaintainer.get(request.getId()));
        try {
            webSocket.sendMessageTo(reply.toJSONString(), request.getId());
        } catch (Exception e) {
           e.printStackTrace();
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void linkTBs(LinkingTB request, StreamObserver<Empty> responseObserver) {
        System.out.println("Server LinkTB Called");
        System.out.println(request.getLinkTBFrom() + "->" + request.getLinkTBTo());
        OnlineTraceHandler traceHandler = new OnlineTraceHandler();
        traceHandler.handleTBLink(request.getId(), request.getLinkTBFrom(), request.getLinkTBTo());
        JSONObject reply = new JSONObject();

        reply.put("type", 9);
        reply.put("linkFrom", request.getLinkTBFrom());
        reply.put("linkTo", request.getLinkTBTo());
        reply.put("data", globalDebugMaintainer.get(request.getId()));
        try {
            webSocket.sendMessageTo(reply.toJSONString(), request.getId());
        } catch (Exception e) {
           e.printStackTrace();
        }
        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void afterDebugMode(Int32Value request, StreamObserver<Empty> responseObserver) {

        responseObserver.onNext(Empty.newBuilder().build());
        responseObserver.onCompleted();
    }
}
