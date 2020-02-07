package com.nf.skateboard.controller.user;

import com.nf.skateboard.service.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl service;

    @RequestMapping("/homepage")
    public String homepage() {
        System.out.println("正在跳转到个人主页...");
        return "homepage";
    }
//    @RequestMapping("/updateUserInfo")
//    public ResponseVO updateUserInfo(UserInfo userInfo) {
//
//        int i = service.updateUserInfo(userInfo);
//        String code = null;
//        String msg = null;
//        Object data = null;
//        if (i != 0) {
//            code = "200";
//            msg = "";
//        }
//        return ResponseVO.newBuilder().code(code).msg(msg).data(data).build();
//    }

}
