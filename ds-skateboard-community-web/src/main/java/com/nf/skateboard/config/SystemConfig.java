package com.nf.skateboard.config;

import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.MultipartConfig;


public class SystemConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    // 只有一个容器时,配置一个就可以了
    // 配置 spring 上下文
    protected Class<?>[] getRootConfigClasses() {
        System.out.println("SystemConfig 使用这个类是不是就不用 web.xml 文件了?");
        return new Class[]{MvcConfig.class};
    }
    // 子容器的配置
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
    // 映射地址
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement());
    }

    // 上传文件需要配置的，
    private MultipartConfigElement getMultipartConfigElement() {
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement("");
        return multipartConfigElement;
    }

}
