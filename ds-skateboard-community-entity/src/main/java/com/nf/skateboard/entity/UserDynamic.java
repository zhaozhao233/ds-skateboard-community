package com.nf.skateboard.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

// 用户发表动态表
public class UserDynamic {

    private Integer userDynamicId;  //用户发表动态ID
    private Integer userId;         //登录用户ID
    private Timestamp dynamicTime;  //发表动态时间
    private String dynamicContent;  //动态的文本内容
    private Integer userImageId;    //用户动态的图片ID

    private UserInfo userInfo;      //用户信息
    private List<UserDynamicImage> dynamicImage;    //用户发表动态的图片集合


    public UserDynamic() {
        Timestamp t = new Timestamp(new Date().getTime());
        this.dynamicTime = t;
        this.userImageId = 0;
    }

    public Integer getUserDynamicId() {
        return userDynamicId;
    }

    public void setUserDynamicId(Integer userDynamicId) {
        this.userDynamicId = userDynamicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getDynamicTime() {
        return dynamicTime;
    }

    public void setDynamicTime(Timestamp dynamicTime) {
        this.dynamicTime = dynamicTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getDynamicContent() {
        return dynamicContent;
    }

    public void setDynamicContent(String dynamicContent) {
        this.dynamicContent = dynamicContent;
    }

    public Integer getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(Integer userImageId) {
        this.userImageId = userImageId;
    }

    public List<UserDynamicImage> getDynamicImage() {
        return dynamicImage;
    }

    public void setDynamicImage(List<UserDynamicImage> dynamicImage) {
        this.dynamicImage = dynamicImage;
    }

    @Override
    public String toString() {
        return "UserDynamic{" +
                "userDynamicId=" + userDynamicId +
                ", userId=" + userId +
                ", dynamicTime=" + dynamicTime +
                ", dynamicContent='" + dynamicContent + '\'' +
                ", userImageId=" + userImageId +
                ", userInfo=" + userInfo +
                ", dynamicImage=" + dynamicImage +
                '}';
    }
}
