package com.nf.skateboard.entity;

import java.sql.Timestamp;

// 用户发表的精彩时刻表
public class UserVideo {
    private Integer userWonderfulMomentId;  // 精彩时刻表ID
    private Integer userId;                 // 用户ID
    private String wonderfulMomentText;     // 精彩时刻的文本内容
    private String wonderfulMomentVideoUrl; // 精彩时刻的视频url
    private Timestamp wonderfulMomentTime;  // 发表时间

    public UserVideo() {

    }

    public Integer getUserWonderfulMomentId() {
        return userWonderfulMomentId;
    }

    public void setUserWonderfulMomentId(Integer userWonderfulMomentId) {
        this.userWonderfulMomentId = userWonderfulMomentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getWonderfulMomentText() {
        return wonderfulMomentText;
    }

    public void setWonderfulMomentText(String wonderfulMomentText) {
        this.wonderfulMomentText = wonderfulMomentText;
    }

    public String getWonderfulMomentVideoUrl() {
        return wonderfulMomentVideoUrl;
    }

    public void setWonderfulMomentVideoUrl(String wonderfulMomentVideoUrl) {
        this.wonderfulMomentVideoUrl = wonderfulMomentVideoUrl;
    }

    public Timestamp getWonderfulMomentTime() {
        return wonderfulMomentTime;
    }

    public void setWonderfulMomentTime(Timestamp wonderfulMomentTime) {
        this.wonderfulMomentTime = wonderfulMomentTime;
    }

    @Override
    public String toString() {
        return "UserVideo{" +
                "userWonderfulMomentId=" + userWonderfulMomentId +
                ", userId=" + userId +
                ", wonderfulMomentText='" + wonderfulMomentText + '\'' +
                ", wonderfulMomentVideoUrl='" + wonderfulMomentVideoUrl + '\'' +
                ", wonderfulMomentTime=" + wonderfulMomentTime +
                '}';
    }
}
