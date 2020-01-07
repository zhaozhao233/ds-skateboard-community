package com.nf.skateboard.interceptor;

import com.nf.skateboard.entity.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

public class LoginInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("-----debug: 用户还未登录，请先进行登录！");
            response.sendRedirect(request.getContextPath()+"/user/login");
        } else {
            System.out.println("-----debug: 拦截，用户已登陆！");
            return true;
        }

        return false;
    }

}
