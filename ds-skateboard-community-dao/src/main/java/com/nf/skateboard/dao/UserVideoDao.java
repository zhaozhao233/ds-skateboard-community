package com.nf.skateboard.dao;

import com.nf.skateboard.entity.DynamicComment;
import com.nf.skateboard.entity.UserVideo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserVideoDao {

    // 添加一条精彩时刻信息到数据库
    int insertUserVideo(@Param("videos") UserVideo userVideo);
    // 根据视频ID删除一条视频
    int deleteUserWonderfulMoment(@Param("wonderfulMomentId") Integer wonderfulMomentId);
    // 根据视频ID查找一条视频
    UserVideo getWonderfulMomentByWonderfulMomentId(@Param("wonderfulMomentId")Integer wonderfulMomentId);
    // 添加评论
    int insertDynamicComment(@Param("comment") DynamicComment dynamicComment);
    // 根据动态ID和评论级别查询评论信息
    List<DynamicComment> listDynamicComment(@Param("dynamicId") Integer dynamicId, @Param("commentLevel") Integer commentLevel);
    // 根据评论ID获取子评论
    List<DynamicComment> listChileCommentByCommentId(@Param("commentParentId")Integer commentParentId);
    // 根据评论ID获取某条评论
    DynamicComment getDynamicCommentByCommentId(@Param("commentId") Integer commentId);
}
