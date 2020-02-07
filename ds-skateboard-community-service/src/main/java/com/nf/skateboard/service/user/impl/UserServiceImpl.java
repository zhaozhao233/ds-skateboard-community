package com.nf.skateboard.service.user.impl;

import com.nf.skateboard.dao.UserDao;
import com.nf.skateboard.entity.UserInfo;
import com.nf.skateboard.entity.UserLoginInfo;
import com.nf.skateboard.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    public List<UserLoginInfo> listUserLoginInfo(int pageNum, int pageSize) {
        return dao.listUserLoginInfo(pageNum,pageSize);
    }



    public List<UserInfo> listUserInfo(int pageNum, int pageSize) {
        return dao.listUserInfo(pageNum,pageSize);
    }

    public UserLoginInfo getUserLoginInfoById(Integer userId) {
        return dao.getUserLoginInfoById(userId);
    }

    public UserLoginInfo getUserLoginInoByPhone(String userPhone) {
        return dao.getUserLoginInfoByPhone(userPhone);
    }

    public UserLoginInfo getUserLoginInfoByAccount(UserLoginInfo userLoginInfo) {
        return dao.getUserLoginInfoByAccount(userLoginInfo);
    }

    public UserInfo getUserInfoById(Integer userId) {
        return dao.getUserInfoById(userId);
    }

    public int insertUserLoginInfo(UserLoginInfo userLoginInfo) {
        return dao.insertUserLoginInfo(userLoginInfo);
    }

    public int insertUserInfo(UserInfo userInfo) {
        return dao.insertUserInfo(userInfo);
    }

    public int updateUserLoginInfo(UserLoginInfo userLoginInfo) {
        return dao.updateUserLoginInfo(userLoginInfo);
    }

    public int updateUserInfo(UserInfo userInfo) {
        return dao.updateUserInfo(userInfo);
    }
}
