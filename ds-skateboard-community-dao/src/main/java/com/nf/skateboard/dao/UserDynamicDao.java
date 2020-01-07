package com.nf.skateboard.dao;

import com.nf.skateboard.entity.DynamicComment;
import com.nf.skateboard.entity.UserDynamic;
import com.nf.skateboard.entity.UserDynamicImage;
import com.nf.skateboard.entity.UserLoginInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDynamicDao {
    // 添加用户发表的动态
    int insertUserDynamic(@Param("userDynamic") UserDynamic userDynamic);
    // 添加用户发表动态的图片
    int insertUserDynamicImage(@Param("userDynamicImage") UserDynamicImage userDynamicImage);

    int insertDynamicComment(@Param("comment") DynamicComment dynamicComment);

    UserDynamic getUserDynamicByDynamicId(@Param("dynamicId") Integer dynamicId);
    // 根据用户ID查询用户发表的动态
    List<UserDynamic> listUserDynamicByUserId(@Param("userId") Integer userId);
    // 根据用户发表动态表的图片ID查询图片
    List<UserDynamicImage> listUserDynamicImageByUserImageId(@Param("userImageId") Integer userImageId);
    // 根据动态ID和评论级别查询评论信息
    List<DynamicComment> listDynamicComment(@Param("dynamicId") Integer dynamicId,@Param("commentLevel") Integer commentLevel);
    // 根据评论ID获取子评论
    List<DynamicComment> listChileCommentByCommentId(@Param("commentParentId")Integer commentParentId);

    DynamicComment getDynamicCommentByCommentId(@Param("commentId") Integer commentId);

    // 修改发表的动态图片表,使得用户动态表里面得图片ID和发表动态图片表的id 一致
    int updateUserDynamicImage(@Param("listImageId")List<Integer> listImageId);

    int deleteUserDynamicById(@Param("dynamicId")Integer dynamicId);

    int deleteDynamicImageByImageId(@Param("imageId")Integer imageId);
}
