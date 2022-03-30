package com.loongson.debug;

import com.google.protobuf.Int32Value;
import com.loongson.debug.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class grpcSimulate extends Thread {
    private static boolean grpc = true;
    private static int id;
    private static boolean canStart = false;
    private static long breakPointAddress;
    private static boolean canExecute = false;
    private static boolean DEBUG = false;
    private static boolean initialized = false;
    /**
     * 1. 不在运行中
     * </br>
     * 2. 运行中但是没到断点
     * 3. 到断点，正在
     * 4. 执行下一条信息
     * 5.
     */
    private static int debugState;
    private static long currentAddress;
    static ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50071).usePlaintext().build();
    static DebugServiceGrpc.DebugServiceBlockingStub stub = DebugServiceGrpc.newBlockingStub(channel);


    public static void main(String[] args) throws InterruptedException {
        //初始化多线程
        Thread synchronizeVarThread = new Thread() {
            public void run() {

                boolean stop = false;
                while (!stop) {
                    //轮询获取能否开始执行的信息
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    SynchronizeVarReply synchronizeVarReply = stub.synchronizeVar(Int32Value.newBuilder().setValue(id).build());
                    if (synchronizeVarReply.getCanExecute()) {
                        //可以开始执行
                        //同步状态
                        DEBUG = synchronizeVarReply.getDEBUG();
                        breakPointAddress = synchronizeVarReply.getBreakPointAddress();
                        stop = true;
                    }
                }
            }
        };

        Thread waitStartThread = new Thread() {
            //只需要同步是否start
            public void run() {
                Int32Value init = stub.init(null);
                id = init.getValue();

                boolean stop = false;
                while (!stop) {
                    //轮询获取能否开始执行的信息
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    CanStart _canStart = stub.getCanStart(Int32Value.newBuilder().setValue(id).build());
                    if (_canStart.getCanStart()) {
                        //可以开始执行
                        //初始化相应参数
                        Address breakPoint = stub.getBreakPoint(Int32Value.newBuilder().setValue(id).build());
                        breakPointAddress = breakPoint.getAddress();
                        canStart = true;
                        stop = true;
                    }
                }
            }
        };


        for (long i = 1000; i < 1500; i++) {
            if (grpc && !initialized) {
                waitStartThread.start();
                waitStartThread.join();
            }
            if (grpc && !DEBUG) {
                if (i == breakPointAddress) {
                    //这里不需要同步状态，等到断点处一起同步状态
                    DEBUG = true;

                }
            }


            System.out.println("--------print trace-----------");

            //断点处，到达此处时，前端允许执行操作
            //前端要改变调试状态只有在断点处 更改 地址以及和调试状态相关的参数
            if (DEBUG) {
                //拦截
                //发送trace
                //等待执行
                stub.sendTrace(Trace.newBuilder().setId(id).setTrace("hahsdhajshdjasdhjka").build());
                synchronizeVarThread.start();
                synchronizeVarThread.join();
            }
            System.out.println("--------execute tb------------");
            canExecute = false;
        }


    }
}
