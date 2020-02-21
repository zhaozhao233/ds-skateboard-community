package com.nf.skateboard.handler.websocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nf.skateboard.entity.websocket.Message;
import com.nf.skateboard.service.websocket.YouAndMeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component // 添加到 springIOC 容器管理
public class MyWebSocketHandler implements WebSocketHandler {

    @Autowired // 自动注入
    private YouAndMeService youandmeService;

    // 当MyWebSocketHandler类被加载时就会创建该Map,随类而生
    public static final Map<Integer,WebSocketSession> userSocketSessionMap;

    static {
        System.out.println("websocket 处理程序");
        userSocketSessionMap = new HashMap<Integer, WebSocketSession>();
    }

    // 实现握手之后
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        int uid = (Integer) session.getAttributes().get("uid");
        if (userSocketSessionMap.get(uid) == null) {
            userSocketSessionMap.put(uid,session);
        }
    }

    // 发送信息前的处理
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message.getPayloadLength()==0) return;

        // 得到socket通道种的数据并转化为 Message 对象
        Message msg = new Gson().fromJson(message.getPayload().toString(),Message.class);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        msg.setMessageDate(now);
        // 将信息保存至数据库
        youandmeService.addMessage();

        // 发送socket信息
        sendMessageToUser(msg.getToId(),new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(msg)));
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("WebSocket:"+session.getAttributes().get("uid")+"close connection");
        Iterator<Map.Entry<Integer,WebSocketSession>> iterator = userSocketSessionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer,WebSocketSession> entry = iterator.next();
            if (entry.getValue().getAttributes().get("uid")==session.getAttributes().get("uid")) {
                userSocketSessionMap.remove(session.getAttributes().get("uid"));
                System.out.println("WebSocket in staticMap:"+session.getAttributes().get("uid")+"removed");
            }
        }

    }

    public boolean supportsPartialMessages() {
        return false;
    }

    // 发送信息的实现
    public void sendMessageToUser(int uid, TextMessage message) throws IOException {
        WebSocketSession session = userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage(message);
        }
    }

}









