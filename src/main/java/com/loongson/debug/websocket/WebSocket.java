package com.loongson.debug.websocket;

import com.alibaba.fastjson.JSONObject;
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

@ServerEndpoint("/websocket/{username}")
@Component
public class WebSocket {
    private static int onlineCount = 0;
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    private Session session;
    private String username;
    private static GlobalDebugMaintainer globalDebugMaintainer = GlobalDebugMaintainer.getInstance();

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;

        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接" + username);
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
        System.out.println("已断开" + username);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(message);
        int type = (int) jsonObject.get("type");
        int id = (int) jsonObject.get("id");
        switch (type) {
            case 0:
                long address = Long.parseLong((String) jsonObject.get("address"));
                setBreakPoint(id, address);
                break;
            case 1:
                nextStep(id);
                break;
            case 2:
                runToNextBreakPoint(id);
                break;
            case 3:
                runToEnd(id);
                break;
        }
        session.getAsyncRemote().sendText(message);

    }

    public void setBreakPoint(int id, long address) {
        globalDebugMaintainer.get(id).setBreakPointAddress(address);
    }

    public void nextStep(int id) {
        //设置canExecute为true
        globalDebugMaintainer.get(id).setCanExecute(true);
    }

    public void runToNextBreakPoint(int id) {
        //关闭单步调试模式，使调试器能够比较执行地址
        globalDebugMaintainer.get(id).setDEBUG(false);
    }

    public void runToEnd(int id) {
        globalDebugMaintainer.get(id).setBreakPointAddress(-1);
        globalDebugMaintainer.get(id).setDEBUG(false);
    }


    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        // session.getAsyncRemote().sendText(message);
        for (WebSocket item : clients.values()) {
            if (item.username.equals(To)) item.session.getAsyncRemote().sendText(message);
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

    public static synchronized Map<String, WebSocket> getClients() {
        return clients;
    }
}
