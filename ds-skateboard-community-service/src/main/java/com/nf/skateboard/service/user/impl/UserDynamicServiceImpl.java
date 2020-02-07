package com.nf.skateboard.service.user.impl;

import com.nf.skateboard.dao.UserDao;
import com.nf.skateboard.dao.UserDynamicDao;
import com.nf.skateboard.entity.*;
import com.nf.skateboard.service.user.UserDynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDynamicServiceImpl implements UserDynamicService {

    @Autowired
    private UserDynamicDao dao;
    @Autowired
    private UserDao userDao;

    // 添加用户发表的动态
    public int insertUserDynamic(UserDynamic userDynamic) {
        return dao.insertUserDynamic(userDynamic);
    }
    // 添加用户发表动态的图片
    public int insertUserDynamicImage(UserDynamicImage userDynamicImage) {
        return dao.insertUserDynamicImage(userDynamicImage);
    }

    // 添加用户动态的评论
    public int insertDynamicComment(DynamicComment dynamicComment) {
        return dao.insertDynamicComment(dynamicComment);
    }

    public UserDynamic getUserDynamicByDynamicId(Integer dynamicId) {
        return dao.getUserDynamicByDynamicId(dynamicId);
    }

    // 根据用户ID查询用户发表的动态
    public List<UserDynamic> listUserDynamicByUserId(Integer userId) {
        System.out.println("---根据用户ID查询用户发表的动态---");
        return dao.listUserDynamicByUserId(userId);
    }
    // 根据用户发表动态表的图片ID查询图片
    public List<UserDynamicImage> listUserDynamicImageByUserImageId(Integer userImageId) {
        return dao.listUserDynamicImageByUserImageId(userImageId);
    }

    // 根据动态Id，评论级别查询评论
    public List<DynamicComment> listDynamicCommentByDynamicIdAndLevel(Integer dynamicId,Integer commentLevel) {
        List<DynamicComment> dynamicComments = dao.listDynamicComment(dynamicId, commentLevel);
        for (int i = 0; i < dynamicComments.size(); i++) {
            DynamicComment dynamicComment = dynamicComments.get(i);
            UserInfo userInfoById = userDao.getUserInfoById(dynamicComment.getUserId());
            dynamicComment.setUserInfo(userInfoById);
        }
        return dynamicComments;
    }

    // 根据评论ID查找子评论
    public List<DynamicComment> listDynamicChileCommentByCommentId(Integer commentId) {
        List<DynamicComment> dynamicComments = listChileCommentByCommentId(commentId);
        for (int i = 0; i < dynamicComments.size(); i++) {
            DynamicComment dynamicComment = dynamicComments.get(i);
            UserInfo userInfoById = userDao.getUserInfoById(dynamicComment.getUserId());
            dynamicComment.setUserInfo(userInfoById);
        }
        return dynamicComments;
    }

    public List<DynamicComment> listChileCommentByCommentId(Integer commentParentId) {
        return dao.listChileCommentByCommentId(commentParentId);
    }

    // 根据动态评论Id获取评论
    public DynamicComment getDynamicCommentByCommentId(Integer commentId) {
        return dao.getDynamicCommentByCommentId(commentId);
    }

    // 修改发表的动态图片表,使得用户动态表里面的图片ID和发表动态图片表的id 一致
    public int updateUserDynamicImage(List<Integer> integerList) {
        return dao.updateUserDynamicImage(integerList);
    }

    public int deleteUserDynamicById(Integer dynamicId) {
        return dao.deleteUserDynamicById(dynamicId);
    }

    public int deleteDynamicImageByImageId(Integer imageId) {
        return dao.deleteDynamicImageByImageId(imageId);
    }

    // 删除动态，并且删除相关图片
    @Transactional
    public int deleteUserDynamicAndImageByDynamicId(Integer dynamicId) {
        UserDynamic userDynamicByDynamicId = dao.getUserDynamicByDynamicId(dynamicId);
        int i = dao.deleteDynamicImageByImageId(userDynamicByDynamicId.getUserImageId());
        int i1 = dao.deleteUserDynamicById(dynamicId);
        return i1;
    }


    // 添加用户发表的动态，将图片的第一个自增ID作为动态表寻找图片的ID，使用事务
    @Transactional
    public int insertUserDynamicAndDynamicImage(List<UserDynamicImage> userDynamicImages,UserDynamic userDynamic) {
        for (UserDynamicImage userDynamicImage : userDynamicImages) {
            System.out.println("要上传的图片："+userDynamicImage);
        }
        System.out.println("要发表的动态:"+userDynamic);

        List<Integer> integers = new ArrayList<Integer>();
        if (userDynamicImages != null && userDynamicImages.size() > 0) {
            System.out.println("发表的动态有图片");
            for (int i = 0; i < userDynamicImages.size(); i++) {
                int i1 = dao.insertUserDynamicImage(userDynamicImages.get(i));
                integers.add(userDynamicImages.get(i).getDynamicImageId());
            }

            System.out.println("获得的自增ID为："+integers.get(0));
            int i = this.updateUserDynamicImage(integers);
            userDynamic.setUserImageId(integers.get(0));
        }

        int i = dao.insertUserDynamic(userDynamic);
        return i;
    }

    /**
     *
     * @param commentContent 评论内容
     * @param dynamicId 用户动态ID
     * @param replyCommentId 要回复的评论ID
     * @param userId 发表评论用户信息
     * @return
     */
    // 发表评论，如果有父评论就把评论级别设置为,父评论级别+1
    @Transactional
    public int insertDynamicCommentForCommentIdAndContent(String commentContent, Integer dynamicId, Integer replyCommentId, Integer userId) {
        DynamicComment dynamicComment = new DynamicComment();
        dynamicComment.setUserId(userId);         //评论的用户ID
        dynamicComment.setCommentContent(commentContent);   //评论内容
        dynamicComment.setUserDynamicId(dynamicId);         //要评论的动态ID
        System.out.println("评论内容为"+commentContent+"动态ID为"+dynamicId+"可能要评论的评论ID"+replyCommentId+"发表评论的用户"+userId);
        if (replyCommentId != null) {
            System.out.println("父评论不为空，设置评论级别...");
            // 根据传过来的父评论Id,获取父评论信息
            DynamicComment dynamicCommentByCommentId = getDynamicCommentByCommentId(replyCommentId);
            System.out.println("根据要评论的评论Id获取的评论信息"+dynamicCommentByCommentId);
            dynamicComment.setCommentParentId(replyCommentId);  // 设置父评论ID
            dynamicComment.setCommentLevel(dynamicCommentByCommentId.getCommentLevel()+1);  // 设置评论级别为父评论级别+1
        } else {
            dynamicComment.setCommentLevel(1);
        }
        int i = insertDynamicComment(dynamicComment);
        return i;

    }

}
