package com.nf.skateboard.controller.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class MyWebSocketController {
    @RequestMapping("/ws")
    public String goToFriendChat() {
        return "websocket/friendchat";
    }
}
