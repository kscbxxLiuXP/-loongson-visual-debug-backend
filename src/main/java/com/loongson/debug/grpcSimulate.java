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
    private static Object lock = new Object();
    static ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", 50071).usePlaintext().build();
    static DebugServiceGrpc.DebugServiceBlockingStub stub = DebugServiceGrpc.newBlockingStub(channel);


    public static void main(String[] args) throws InterruptedException {
        for (long i = 1000; i < 1500; i++) {
            if (grpc && !initialized) {
                //waitStartThread
                new Thread() {
                    //只需要同步是否start
                    public void run() {
                        System.out.println("进入wait start 线程");
                        //此处不需要设置debugstate，server初始化时会在此处设置为1
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
                                initialized = true;
                                //设置状态执行中
                                stub.setDebugState(DebugState.newBuilder().setId(id).setState(2).build());
                                //可以开始执行
                                //初始化断点地址
                                Address breakPoint = stub.getBreakPoint(Int32Value.newBuilder().setValue(id).build());
                                System.out.println("断点" + breakPoint.getAddress());
                                breakPointAddress = breakPoint.getAddress();
                                canStart = true;
                                stop = true;
                            }
                        }

                        System.out.println("waitStart 线程结束");
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                }.start();

                try {
                    synchronized (lock) {//这里也是一样
                        lock.wait();//主线程等待
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if (grpc && !DEBUG && i == breakPointAddress) {
                //这里不需要同步状态，等到断点处一起同步状态
                DEBUG = true;
            }


            System.out.println("--------print trace-----------");

            //断点处，到达此处时，前端允许执行操作
            //前端要改变调试状态只有在断点处 更改 地址以及和调试状态相关的参数
            if (DEBUG) {
                //拦截
                //发送trace
                stub.sendTrace(Trace.newBuilder().setId(id).setAddress("3745855").setRegisters("00000000 00000000 00000000 00000000 00000000 00000000 00000000 3ffff200 3f78d0b0 00000202").build());
                //更新地址
                stub.setCurrentAddress(Address.newBuilder().setId(id).setAddress(i).build());
                //同步DEBUG状态
                stub.setDEBUGTrue(Int32Value.newBuilder().setValue(id).build());
                //更新debugState
                stub.setDebugState(DebugState.newBuilder().setId(id).setState(3).build());

                //等待执行
                //synchronizeVarThread.start();
                new Thread() {
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
                        synchronized (lock) {
                            lock.notify();
                        }
                    }
                }.start();
                try {
                    synchronized (lock) {//这里也是一样
                        lock.wait();//主线程等待
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                canExecute = false;
                stub.setCanExecuteFalse(Int32Value.newBuilder().setValue(id).build());
                stub.setDebugState(DebugState.newBuilder().setId(id).setState(2).build());
            }

            System.out.println("--------execute tb------------");
            Thread.sleep(10);
            stub.linkTBs(LinkingTB.newBuilder().setId(id).setLinkTBFrom("374555").setLinkTBTo("1515615").build());
        }
        stub.executeEnd(Int32Value.newBuilder().setValue(id).build());


    }
}
