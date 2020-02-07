package com.nf.skateboard.service.user;

import com.nf.skateboard.entity.DynamicComment;
import com.nf.skateboard.entity.UserDynamic;
import com.nf.skateboard.entity.UserDynamicImage;

import java.util.List;

public interface UserDynamicService {

    int insertUserDynamic(UserDynamic userDynamic);

    int insertUserDynamicImage(UserDynamicImage userDynamicImage);

    int insertDynamicComment(DynamicComment dynamicComment);

    UserDynamic getUserDynamicByDynamicId(Integer dynamicId);

    List<UserDynamic> listUserDynamicByUserId(Integer userId);

    List<UserDynamicImage> listUserDynamicImageByUserImageId(Integer userImageId);

    List<DynamicComment> listDynamicCommentByDynamicIdAndLevel(Integer dynamicId,Integer commentLevel);

    List<DynamicComment> listChileCommentByCommentId(Integer commentParentId);

    DynamicComment getDynamicCommentByCommentId(Integer commentId);

    int updateUserDynamicImage(List<Integer> integerList);

    int deleteUserDynamicById(Integer dynamicId);

    int deleteDynamicImageByImageId(Integer imageId);

}
