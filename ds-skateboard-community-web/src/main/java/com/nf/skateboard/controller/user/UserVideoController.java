package com.nf.skateboard.controller.user;

import com.nf.skateboard.entity.UserDynamic;
import com.nf.skateboard.entity.UserDynamicImage;
import com.nf.skateboard.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // 被springIOC容器管理
@RequestMapping("/user")
public class UserVideoController {

    @RequestMapping("/viodes")
    public String userVideo() {
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
        return "forward:/WEB-INF/jsp/menu/video/skateboardVideo.jsp";
    }
}
