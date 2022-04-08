package com.loongson.debug.websocket;

import com.alibaba.fastjson.JSONObject;
import com.google.api.client.json.Json;
import com.loongson.debug.helper.GlobalDebugMaintainer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket/{id}")
@Component
public class WebSocket {
    private static int onlineCount = 0;
    private static Map<Integer, WebSocket> clients = new ConcurrentHashMap<Integer, WebSocket>();
    private Session session;
    private int id;
    private static GlobalDebugMaintainer globalDebugMaintainer = GlobalDebugMaintainer.getInstance();

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) throws IOException {
        this.id = Integer.parseInt(id);
        this.session = session;

        addOnlineCount();
        clients.put(this.id, this);
        System.out.println("已连接" + id);
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(id);
        subOnlineCount();
        System.out.println("已断开" + id);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(message);
        int type = (int) jsonObject.get("type");
        int id = (int) jsonObject.get("id");
        JSONObject reply = new JSONObject();
        switch (type) {
            case -1:
                reply.put("type", -1);
                reply.put("code", 1);
                reply.put("msg", "连接成功");
                break;
            case 0:
                //设置断点
                reply.put("type", 0);
                long address = Long.parseLong((String) jsonObject.get("address"));
                setBreakPoint(id, address);
                reply.put("code", 1);
                break;
            case 1:
                //下一步
                reply.put("type", 1);

                nextStep(id);
                break;
            case 2:
                //运行到下一个断点
                reply.put("type", 2);
                runToNextBreakPoint(id);
                break;
            case 3:
                //运行到结束
                reply.put("type", 3);
                runToEnd(id);
                break;
            case 4:
                //StartRun
                reply.put("type", 4);
                reply.put("code", 1);
                startRun(id);
                break;
            case 5:
                //stopRun
                reply.put("type", 5);
                stopRun(id);
                break;
            // case 6 被占用，更新debugState
            // case 7 被占用，Latx运行结束，Latx rpc发送信号
            // case 8 被占用，Latx发送trace回来
            case 9:
                break;
        }
        reply.put("data", globalDebugMaintainer.get(id));
        session.getAsyncRemote().sendText(reply.toJSONString());

    }

    public void startRun(int id) {
        globalDebugMaintainer.get(id).setCanStart(true);
        globalDebugMaintainer.get(id).setDebugState(7);
    }

    public void stopRun(int id) {
        globalDebugMaintainer.get(id).setCanExecute(true);
        globalDebugMaintainer.get(id).setBreakPointAddress(-1);
        globalDebugMaintainer.get(id).setDEBUG(false);
        globalDebugMaintainer.get(id).setDebugState(5);
    }

    public void setBreakPoint(int id, long address) {
        globalDebugMaintainer.get(id).setBreakPointAddress(address);
    }

    public void nextStep(int id) {
        //设置canExecute为true
        globalDebugMaintainer.get(id).setCanExecute(true);
        //设置为7 等待latx更新状态
        globalDebugMaintainer.get(id).setDebugState(7);

    }

    public void runToNextBreakPoint(int id) {
        //关闭单步调试模式，使调试器能够比较执行地址
        globalDebugMaintainer.get(id).setDEBUG(false);
        globalDebugMaintainer.get(id).setCanExecute(true);

        //设置为7等待latx更新状态
        globalDebugMaintainer.get(id).setDebugState(7);

    }

    public void runToEnd(int id) {
        globalDebugMaintainer.get(id).setCanExecute(true);
        globalDebugMaintainer.get(id).setBreakPointAddress(-1);
        globalDebugMaintainer.get(id).setDEBUG(false);
        globalDebugMaintainer.get(id).setDebugState(7);

    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, int To) throws IOException {
        // session.getBasicRemote().sendText(message);
        // session.getAsyncRemote().sendText(message);
        for (WebSocket item : clients.values()) {
            if (item.id == To) item.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }


    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }

    public static synchronized Map<Integer, WebSocket> getClients() {
        return clients;
    }
}
