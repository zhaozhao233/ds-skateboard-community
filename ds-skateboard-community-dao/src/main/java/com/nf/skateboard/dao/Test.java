package com.nf.skateboard.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test {

    @org.junit.Test
    public void test1() {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败");
        }
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = factory.openSession(true);

        UserDao mapper = session.getMapper(UserDao.class);
        // 代理   mapper 是代理人
        InvocationHandler handler = new MapperInterceptor(session,mapper);
        UserDao o = (UserDao) Proxy.newProxyInstance(UserDao.class.getClassLoader(), new Class[]{UserDao.class}, handler);

    }

    // 代理
    public static class MapperInterceptor implements InvocationHandler {
        private SqlSession session;
        private Object object;
        public MapperInterceptor(SqlSession session,Object target) {
            this.session = session;
            this.object = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            try {
                // 代理
                return method.invoke(object,args);
            } finally {
                // 关闭连接
                session.close();
                System.out.println("使用代理将连接关闭");
            }

        }
    }
}
