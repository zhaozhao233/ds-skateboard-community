package com.nf.skateboard.entity;


import java.sql.Timestamp;
import java.util.Date;

// 用户信息表1
public class UserInfo {

    private Integer userInfoId;     //信息表ID
    private Integer userId;         //登录表中的用户ID
    private String userName;        //昵称
    private String userPhone;       //手机号
    private String userEmail;       //邮箱
    private char userSex;           //性别
    private String userImageUrl;    //头像URL
    private Integer userSkateGrade; //滑板称号等级
    private Integer userExp;        //账号经验值
    private Integer userBalance;    //余额
    private Integer isOfficial;     //是否为官方账号
    private Timestamp registerTime; //注册时间


    public UserInfo() {

    }

    public UserInfo(boolean isdefault) {
        if (isdefault) {
            this.userSex = 1;
            this.userImageUrl = "";
            this.userSkateGrade = 1;
            this.userExp = 1;
            this.userBalance = 0;
            this.isOfficial = 0;
            this.registerTime = new Timestamp(new Date().getTime());
        }
    }

    public UserInfo(Integer userId,String userImageUrl) {
        this.userId = userId;
        this.userImageUrl = userImageUrl;
    }

    public Integer getUserExp() {
        return userExp;
    }

    public void setUserExp(Integer userExp) {
        this.userExp = userExp;
    }

    public Integer getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(Integer userInfoId) {
        this.userInfoId = userInfoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public char getUserSex() {
        return userSex;
    }

    public void setUserSex(char userSex) {
        this.userSex = userSex;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public Integer getUserSkateGrade() {
        return userSkateGrade;
    }

    public void setUserSkateGrade(Integer userSkateGrade) {
        this.userSkateGrade = userSkateGrade;
    }

    public Integer getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Integer userBalance) {
        this.userBalance = userBalance;
    }

    public Integer getIsOfficial() {
        return isOfficial;
    }

    public void setIsOfficial(Integer isOfficial) {
        this.isOfficial = isOfficial;
    }

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userSex=" + userSex +
                ", userImageUrl='" + userImageUrl + '\'' +
                ", userSkateGrade=" + userSkateGrade +
                ", userExp=" + userExp +
                ", userBalance=" + userBalance +
                ", isOfficial=" + isOfficial +
                ", registerTime=" + registerTime +
                '}';
    }
}
