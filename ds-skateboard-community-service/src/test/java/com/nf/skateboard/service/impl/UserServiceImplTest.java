package com.nf.skateboard.service.impl;

import com.nf.skateboard.config.DaoSpringConfig;
import com.nf.skateboard.dao.UserDao;
import com.nf.skateboard.entity.UserInfo;
import com.nf.skateboard.entity.UserLoginInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoSpringConfig.class)
public class UserServiceImplTest {

    @Autowired
    private UserDao dao;

    @Test
    public void listUserLoginInfo() {
        List<UserLoginInfo> userLoginInfos = dao.listUserLoginInfo(1, 10);
        for (UserLoginInfo userLoginInfo : userLoginInfos) {
            System.out.println(userLoginInfo);
        }
    }

    @Test
    public void listUserInfo() {
        List<UserInfo> userInfos = dao.listUserInfo(1, 10);
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo);
        }
    }

    @Test
    public void getUserLoginInfoById() {
    }

    @Test
    public void getUserLoginInoByPhone() {
    }

    @Test
    public void getUserLoginInfoByAccount() {
        UserLoginInfo user = new UserLoginInfo();
        user.setPassword("baigei");
        user.setUserEmail("wdnmd");
        UserLoginInfo userLoginInfoByAccount = dao.getUserLoginInfoByAccount(user);
        System.out.println(userLoginInfoByAccount);
    }

    @Test
    public void getUserInfoById() {
    }

    @Test
    public void insertUserLoginInfo() {
    }

    @Test
    public void insertUserInfo() {
    }

    @Test
    public void updateUserLoginInfo() {
        UserLoginInfo user = new UserLoginInfo();
        user.setUserEmail("wdnmd");
        user.setPassword("baigei");
        dao.updateUserLoginInfo(user);
    }
}