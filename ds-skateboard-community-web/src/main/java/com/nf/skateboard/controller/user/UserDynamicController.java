package com.nf.skateboard.controller.user;

import com.nf.skateboard.entity.DynamicComment;
import com.nf.skateboard.entity.UserDynamic;
import com.nf.skateboard.entity.UserDynamicImage;
import com.nf.skateboard.entity.UserInfo;
import com.nf.skateboard.service.impl.UserDynamicServiceImpl;
import com.nf.skateboard.service.impl.UserServiceImpl;
import com.nf.skateboard.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserDynamicController {
    @Autowired
    private UserDynamicServiceImpl dynamicService;
    @Autowired
    private UserServiceImpl service;

    // 跳转到用户发表动态的界面（用于异步）
    @RequestMapping("/dynamic")
    public String goToDynamic(Integer userId, Model model) {
        List<UserDynamic> userDynamics = dynamicService.listUserDynamicByUserId(userId);
        UserInfo userInfoById = service.getUserInfoById(userId);
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserName("今晚打老虎");
//        userInfo.setUserImageUrl("head_portrait/girl1.jpg");
        for (int i = 0; i < userDynamics.size(); i++) {
            UserDynamic userDynamic = userDynamics.get(i);
            userDynamic.setUserInfo(userInfoById);
            List<UserDynamicImage> userDynamicImages = dynamicService.listUserDynamicImageByUserImageId(userDynamic.getUserImageId());
            // 添加动态要用的图片
            userDynamic.setDynamicImage(userDynamicImages);
        }
        model.addAttribute("userDynamic",userDynamics);
        return "forward:/WEB-INF/jsp/userDynamic.jsp";
    }

    //跳转到动态评论界面（用于异步）
    @RequestMapping("/comment")
    public String goToComment(Integer dynamicId,Model model) {
        // 获取动态Id为dynamicId,评论级别为1的评论
        List<DynamicComment> dynamicComments = dynamicService.listDynamicCommentByDynamicIdAndLevel(dynamicId, 1);
        model.addAttribute("comments",dynamicComments);
        model.addAttribute("dynamicId",dynamicId);
        return "forward:/WEB-INF/jsp/comment.jsp";
    }

    //跳转到动态评论的子评论（用于异步）
    @RequestMapping("/childComment")
    public String goToChildComment(Integer commentId,Model model) {
        // 查找子评论
        List<DynamicComment> dynamicComments = dynamicService.listDynamicChileCommentByCommentId(commentId);
        System.out.println("获取到的子评论有：");
        for (DynamicComment dynamicComment : dynamicComments) {
            System.out.println(dynamicComment);
        }
        System.out.println("------------------------------------------------------------");
        model.addAttribute("chileComment",dynamicComments);
        return "forward:/WEB-INF/jsp/childComment.jsp";
    }


    // 用户删除动态,并且删除相关图片
    @Transactional
    @RequestMapping("/deleteDynamic")
    @ResponseBody
    public ResponseVO deleteDynamic(Integer dynamicId) {
        String code = "200";
        String msg = "删除成功";
        Object data = null;
        int i = dynamicService.deleteUserDynamicAndImageByDynamicId(dynamicId);
        if (i <= 0) {
            code = "500";
            msg = "删除失败";
        }
        return ResponseVO.newBuilder().code(code).msg(msg).data(data).build();
    }

    /**
     *
     * @param commentContent 评论内容
     * @param dynamicId 发表动态ID
     * @param replyCommentId
     * @param session
     * @return
     */
    // 发表评论()
    @RequestMapping("/publishComment")
    @ResponseBody // 不加这个会报 404 错误，
    public ResponseVO publishComment(String commentContent, @RequestParam("dynamicId") Integer dynamicId, Integer replyCommentId,
                                     HttpSession session) {
        System.out.println("评论内容："+commentContent+"评论ID"+dynamicId+"可能要评论的评论ID"+replyCommentId);
        UserInfo user = (UserInfo) session.getAttribute("user");
        System.out.println("user = " + user);
        String code = "200";
        String msg = "发表评论成功";
        Object obj = null;
        int i = dynamicService.insertDynamicCommentForCommentIdAndContent(commentContent, dynamicId, replyCommentId, user.getUserId());
        if (i <= 0) {
            code = "500";
            msg = "发表评论失败";
        }
        return ResponseVO.newBuilder().code(code).msg(msg).build();
    }

    // 获取动态评论
    @RequestMapping("/listDynamicComment")
    @ResponseBody
    public ResponseVO listDynamicComment(Integer dynamicId) {
        List<DynamicComment> dynamicComments = dynamicService.listDynamicCommentByDynamicIdAndLevel(dynamicId, 1);
        String code = "200";
        String msg = "获取发表动态的评论成功";
        Object data = null;
        if (dynamicComments != null) {
            data = dynamicComments;
        }
        return ResponseVO.newBuilder().code(code).msg(msg).data(data).build();
    }



}
