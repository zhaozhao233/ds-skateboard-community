package com.nf.skateboard.interceptor.websocket;

import com.nf.skateboard.entity.UserInfo;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

// websocket握手拦截器
public class MyHandShakeInterceptor implements HandshakeInterceptor {

    // 握手之前
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        System.out.println("Websocket:用户[ID:"+((ServletServerHttpRequest)serverHttpRequest).getServletRequest().getSession(false).getAttribute("user")+"]已经建立连接");
        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            // 标记用户
            UserInfo userInfo = (UserInfo) session.getAttribute("user");
            if (userInfo != null) {
                map.put("uid",userInfo.getUserId());// 为服务器创建WebSocketSession做准备
                System.out.println("用户id"+userInfo.getUserId()+"被加入");
            } else {
                System.out.println("user为空");
                return false;
            }
        }
        return true;
    }

    // 握手之后
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
