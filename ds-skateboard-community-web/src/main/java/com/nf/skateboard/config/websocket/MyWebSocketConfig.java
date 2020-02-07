package com.nf.skateboard.config.websocket;

import com.nf.skateboard.handler.websocket.MyWebSocketHandler;
import com.nf.skateboard.interceptor.websocket.MyHandShakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

// 我的 websocket服务
@Component // 添加到 springIOC 容器管理
@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter {

    @Autowired
    MyWebSocketHandler handler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        // 添加websocket 处理器，添加握手拦截器
        webSocketHandlerRegistry.addHandler(handler,"/ws").addInterceptors(new MyHandShakeInterceptor());

        // 添加websocket 处理器，添加握手拦截器
        webSocketHandlerRegistry.addHandler(handler,"/ws/sockjs").addInterceptors(new MyHandShakeInterceptor()).withSockJS();
    }

}
















