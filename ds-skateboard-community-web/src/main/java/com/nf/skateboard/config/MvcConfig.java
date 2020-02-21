package com.nf.skateboard.config;

import com.nf.skateboard.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc   // 相当于<mvc:annotation-driven>
@Import(com.nf.skateboard.config.DaoSpringConfig.class) //导入另一个配置文件
@ComponentScan({"com.nf.skateboard.service.user.impl","com.nf.skateboard.controller"})
public class MvcConfig implements WebMvcConfigurer {


    // 视图资源解析器
    @Bean
    public InternalResourceViewResolver resourceViewResolver() {
        System.out.println("视图资源解析器启动...");
        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
        resourceViewResolver.setPrefix("/WEB-INF/jsp/");
        resourceViewResolver.setSuffix(".jsp");
        return resourceViewResolver;
    }

    // 访问静态资源
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("访问静态资源");
        // 可以写多个，访问不同的静态资源
        ResourceHandlerRegistration registration1 = registry.addResourceHandler("/skateimage/**");
        registration1.addResourceLocations("F:/skateboard_community_project_image/");

        // 当 url 的格式为 /static/** 的时候,就使用默认的 servlet 不被 spring 管理
        ResourceHandlerRegistration registration = registry.addResourceHandler("/static/**");
        registration.addResourceLocations("classpath:/static/");

        ResourceHandlerRegistration registration2 = registry.addResourceHandler("/img/**");
        registration2.addResourceLocations("classpath:/img/");

    }

    // 拦截器
    public void addInterceptors(InterceptorRegistry registry) {
        // 自己写的拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        // 拦截器注册类
        InterceptorRegistration re = registry.addInterceptor(loginInterceptor);
        re.addPathPatterns("/**");
        re.excludePathPatterns("/user/login","/user/userLogin","/user/logon","/ws","/web/**","/websocket/**");
    }

    // 跨域
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://127.0.0:8848")
//                .allowedMethods("GET","POST","OPTIONS");
    }
}
