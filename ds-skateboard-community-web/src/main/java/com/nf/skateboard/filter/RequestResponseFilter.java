package com.nf.skateboard.filter;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestResponseFilter extends CharacterEncodingFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String encoding = getEncoding();//获取filter配置的编码格式
        if (encoding != null) { //判断是否为空
            if (isForceRequestEncoding() || request.getCharacterEncoding() == null) {
                request.setCharacterEncoding(encoding);
            }
            if (isForceResponseEncoding()) {
                response.setCharacterEncoding(encoding);
            }
            filterChain.doFilter(request,response);
       }
    }


}
