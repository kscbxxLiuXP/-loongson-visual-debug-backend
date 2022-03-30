package com.loongson.debug.grpc;

import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.loongson.debug.helper.GlobalDebugMaintainer;
import io.grpc.stub.StreamObserver;

public class OnlineDebugServer extends DebugServiceGrpc.DebugServiceImplBase {
    private GlobalDebugMaintainer globalDebugMaintainer;

    public OnlineDebugServer() {
        globalDebugMaintainer = GlobalDebugMaintainer.getInstance();
    }

    @Override
    public void init(Empty request, StreamObserver<Int32Value> responseObserver) {
        int id = globalDebugMaintainer.create();
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
    }

    @Override
    public void getCanExecute(Int32Value request, StreamObserver<CanExecute> responseObserver) {
        boolean canExecute = globalDebugMaintainer.get(request.getValue()).isCanExecute();
        responseObserver.onNext(CanExecute.newBuilder().setCanExecute(canExecute).build());
        responseObserver.onCompleted();
    }

    @Override
    public void setDebugState(DebugState request, StreamObserver<Empty> responseObserver) {
        globalDebugMaintainer.get(request.getId()).setDebugState(request.getState());
    }

    @Override
    public void sendTrace(Trace request, StreamObserver<Empty> responseObserver) {

        super.sendTrace(request, responseObserver);
    }
}
