package com.nf.skateboard.dao;

import com.nf.skateboard.entity.UserInfo;
import com.nf.skateboard.entity.UserLoginInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

public interface UserDao {

    List<UserInfo> listUserInfo(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    List<UserLoginInfo> listUserLoginInfo(@Param("pageNum")int pageNum,@Param("pageSize")int pageSize);

    UserLoginInfo getUserLoginInfoById(@Param("userId")int userinfo);

    UserLoginInfo getUserLoginInfoByPhone(@Param("userPhone")String userPhone);

    UserLoginInfo getUserLoginInfoByAccount(@Param("user") UserLoginInfo userLoginInfo);

    UserInfo getUserInfoById(@Param("userId") int userId);

    int insertUserLoginInfo(@Param("user")UserLoginInfo userLoginInfo);

    int insertUserInfo(@Param("user")UserInfo userInfo);

    int updateUserLoginInfo(@Param("user")UserLoginInfo userLoginInfo);

    int updateUserInfo(@Param("user")UserInfo userInfo);
}

