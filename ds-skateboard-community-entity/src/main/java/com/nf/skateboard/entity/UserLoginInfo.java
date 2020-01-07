package com.nf.skateboard.entity;


import java.sql.Timestamp;
import java.util.Date;

//用户登录表
public class UserLoginInfo {

    private Integer userId;     //用户ID
    private String userPhone;   //手机号
    private String userEmail;   //邮箱
    private String password;    //密码
    private Integer userStatus; //用户状态
    private Timestamp lastLoginTime;//最后一次登录时间

    public UserLoginInfo() {
        this.userEmail = "";
        this.userStatus = 1;//yyyy-mm-dd hh:mm:ss.
        Timestamp t = new Timestamp(new Date().getTime());
        this.lastLoginTime = t;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Timestamp getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "userId=" + userId +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", password='" + password + '\'' +
                ", userStatus=" + userStatus +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
