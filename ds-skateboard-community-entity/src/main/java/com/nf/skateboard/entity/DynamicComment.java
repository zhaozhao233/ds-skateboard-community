package com.nf.skateboard.entity;

import java.sql.Timestamp;
import java.util.Date;

// 动态评论表
public class DynamicComment {

    private Integer dynamicCommentId;   //动态评论表自增ID
    private Integer userId;             //评论用户ID
    private Integer userDynamicId;      //要评论的动态ID
    private String commentContent;      //评论内容
    private Timestamp commentTime;      //评论时间
    private Integer commentLevel;       //评论级别
    private Integer commentParentId;    //父级评论ID
    private Integer commentHeat;        //评论热度

    private UserInfo userInfo;          //用户信息

    public DynamicComment() {
        Timestamp t = new Timestamp(new Date().getTime());
        this.commentTime = t;
        this.commentLevel = 1;  //默认评论级别为1
        this.commentHeat = 0;   //默认热度为0
    }


    public Integer getDynamicCommentId() {
        return dynamicCommentId;
    }

    public void setDynamicCommentId(Integer dynamicCommentId) {
        this.dynamicCommentId = dynamicCommentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserDynamicId() {
        return userDynamicId;
    }

    public void setUserDynamicId(Integer userDynamicId) {
        this.userDynamicId = userDynamicId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public Integer getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(Integer commentLevel) {
        this.commentLevel = commentLevel;
    }

    public Integer getCommentParentId() {
        return commentParentId;
    }

    public void setCommentParentId(Integer commentParentId) {
        this.commentParentId = commentParentId;
    }

    public Integer getCommentHeat() {
        return commentHeat;
    }

    public void setCommentHeat(Integer commentHeat) {
        this.commentHeat = commentHeat;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "DynamicComment{" +
                "dynamicCommentId=" + dynamicCommentId +
                ", userId=" + userId +
                ", userDynamicId=" + userDynamicId +
                ", commentContent='" + commentContent + '\'' +
                ", commentTime=" + commentTime +
                ", commentLevel=" + commentLevel +
                ", commentParentId=" + commentParentId +
                ", commentHeat=" + commentHeat +
                ", userInfo=" + userInfo +
                '}';
    }
}
