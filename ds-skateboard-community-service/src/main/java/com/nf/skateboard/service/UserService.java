package com.nf.skateboard.service;

import com.nf.skateboard.entity.UserInfo;
import com.nf.skateboard.entity.UserLoginInfo;


import java.util.List;

public interface UserService {

    // 返回所有用户登录信息
    List<UserLoginInfo> listUserLoginInfo(int pageNum,int pageSize);
    // 返回所有用户信息
    List<UserInfo> listUserInfo(int pageNum,int pageSize);
    // 根据user_id返回用户登录信息
    UserLoginInfo getUserLoginInfoById(Integer userId);
    // 根据user_phone返回用户登录信息
    UserLoginInfo getUserLoginInoByPhone(String userPhone);
    // 根据账号密码返回用户登录信息
    UserLoginInfo getUserLoginInfoByAccount(UserLoginInfo userLoginInfo);
    // 根据user_id返回用户信息
    UserInfo getUserInfoById(Integer userId);
    // 添加用户登录信息
    int insertUserLoginInfo(UserLoginInfo userLoginInfo);
    // 添加用户信息
    int insertUserInfo(UserInfo userInfo);
    // 修改用户登录信息
    int updateUserLoginInfo(UserLoginInfo userLoginInfo);
    // 修改用户信息
    int updateUserInfo(UserInfo userInfo);
}
