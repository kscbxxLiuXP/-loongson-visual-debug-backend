package com.loongson.debug.controller;

import com.loongson.debug.websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 提供对websocket实例对象操作的方法接口
 */
@RestController
@CrossOrigin
@RequestMapping("/socket")
public class WebSocketController {
    @Autowired
    WebSocket webSocket;

    @GetMapping("/sendAll")
    public String sendAll() throws IOException {
        webSocket.sendMessageAll("sendToAll");
        return "send!";
    }

    @GetMapping("/count")
    public int getCount() {
        int onlineCount = WebSocket.getOnlineCount();
        return onlineCount;
    }
}
