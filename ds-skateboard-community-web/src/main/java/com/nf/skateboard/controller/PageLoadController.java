package com.nf.skateboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageLoadController {

    // 跳转到注册页面
    @RequestMapping("/")
    public String goToLogon() {
        System.out.println("前往注册页面");
        return "logon";
    }
}
