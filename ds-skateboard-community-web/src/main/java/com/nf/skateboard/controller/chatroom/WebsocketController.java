package com.nf.skateboard.controller.chatroom;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@RestController
//@CrossOrigin(value = {"ws://127.0.0.1:8848"},
//        methods = {
//                RequestMethod.DELETE,
//                RequestMethod.PUT,
//                RequestMethod.POST,
//                RequestMethod.GET,
//                RequestMethod.OPTIONS,
//                RequestMethod.HEAD
//})
@ServerEndpoint(value = "/websocket/{myWebsocket}",configurator = SpringConfigurator.class)
public class WebsocketController {

    public static Map<String,Session> clients =
            new ConcurrentHashMap<String, Session>();



    /**
     * 打开连接时触发
     * @param myWebsocket
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("myWebsocket") String myWebsocket, Session session) {
        System.out.println("进入："+myWebsocket);
        clients.put(myWebsocket,session);
    }

    /**
     * 收到客户端消息时触发
     * @param myWebsocket
     * @param message
     * @return
     */
    @OnMessage
    public String onMessage(@PathParam("myWebsocket") String myWebsocket,String message) {
        System.out.println("收到客户端消息");
        return "Got your message ("+message+").Thanks!";
    }

    /**
     * 异常时触发
     * @param myWebsocket
     * @param throwable
     */
    @OnError
    public void onError(@PathParam("myWebsocket") String myWebsocket,Throwable throwable) {
        System.out.println("异常时触发");
        clients.remove(myWebsocket);
    }

    /**
     * 关闭连接时触发
     * @param myWebsocket
     */
    @OnClose
    public void onClose(@PathParam("myWebsocket") String myWebsocket) {
        System.out.println("关闭连接");
        clients.remove(myWebsocket);
    }

    /**
     * 将数据传回客户端
     * 异步的方式
     * @param myWebsocket
     * @param message
     */
    public static void broadcast(String myWebsocket,String message) {
        if (clients.containsKey(myWebsocket)) {
            clients.get(myWebsocket).getAsyncRemote().sendText(message);
        } else {
            throw new NullPointerException("["+myWebsocket+"] Connection does not exist");
        }
    }
}
