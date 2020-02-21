package com.nf.skateboard.controller.user;

import com.nf.skateboard.entity.UserInfo;
import com.nf.skateboard.entity.UserLoginInfo;
import com.nf.skateboard.service.user.UserDynamicService;
import com.nf.skateboard.service.user.impl.UserServiceImpl;
import com.nf.skateboard.utils.RandomCode;
import com.nf.skateboard.utils.SendVerificationCode;
import com.nf.skateboard.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

// 用户登录
@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserServiceImpl service;
    @Autowired
    private UserDynamicService dynamicService;

    // 跳转到表情测试页面
    @RequestMapping("/emoji")
    public String goToEmoji() {
        return "emoji";
    }


    // 跳转到注册页面
    @RequestMapping("/logon")
    public String goToLogon() {
        System.out.println("前往注册页面");
        return "logon";
    }

    // 跳转到登录界面
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        System.out.print("前往登录界面");
        System.out.println("服务器地址"+request.getServerName()+"端口号"+request.getServerPort()+"上下文路径/项目文件夹的文件名"+request.getContextPath());
        return "login";
    }

    // 发送短信验证码
    @RequestMapping("/sendVerificationCode")
    @ResponseBody
    public String sendVerificationCode(String phone, HttpSession session) {
        String randomForSix = RandomCode.getRandomForSix();
        SendVerificationCode sendVerificationCode = new SendVerificationCode();
        String s = sendVerificationCode.sendMessage(phone.trim(),randomForSix);
        System.out.println("手机号码phone = " + phone+"//");
        System.out.println("短信验证发送的状态为:"+s);
        if ("ok".equalsIgnoreCase(s)) {
            session.setAttribute("code",randomForSix);
            session.setAttribute("sendtime",System.currentTimeMillis());
            long l = System.currentTimeMillis();
        }
        return s.equalsIgnoreCase("ok")?"true":"false";
    }

    // 验证短信验证码
    @RequestMapping("/checkVerificationCode")
    @ResponseBody
    public String checkVerificationCode(String code,HttpSession session) {
        System.out.println("验证短信验证码:"+code);
        String code1 = String.valueOf(session.getAttribute("code"));
        // session 上保存的时间戳
        Double sendtime = Double.valueOf((Long) session.getAttribute("sendtime"));
        // 现在的时间戳（1秒=1000毫秒）
        Double sendtime2 = Double.valueOf(System.currentTimeMillis());
        System.out.println("用户传过来的验证码："+code+"||session上保存的验证码："+code1);
        System.out.println("用户传操作的时间戳："+sendtime2+"||session上保存的时间戳："+sendtime);
        if (code.equalsIgnoreCase(code1)) {
            System.out.println("-----debug: 一层验证通过，验证码一致");
            if (sendtime2-sendtime <= (1000*600)) {
                System.out.println("-----debug: 二层验证通过，验证码在有效时间内");
                return "true";
            } else {
                System.out.println("-----debug: 二层验证失败，验证码已失效");
                return "false";
            }
        } else {
            System.out.println("-----debug: 一层验证失败，验证码不一致");
            return "false";
        }

    }

    // 判断是不是第一次注册
    @RequestMapping("/checkUserByPhone")
    @ResponseBody
    public String checkUserByPhone(@RequestBody String phone) {
        UserLoginInfo userLoginInoByPhone = service.getUserLoginInoByPhone(phone);
        System.out.println("userLoginInoByPhone = " + userLoginInoByPhone);
        if (userLoginInoByPhone.equals(null)) {
            System.out.println("-----debug: 新用户注册");
            return "true";
        } else {
            System.out.println("-----debug: 该账号已经注册过了");
            return "false";
        }
    }

    // 完成验证，添加用户信息到数据库
    @Transactional  // 事务方法
    @PostMapping("/userLogon")
    @ResponseBody
    public String logon(UserLoginInfo userLoginInfo, HttpSession session) {
        System.out.println("将新用户添加到数据库");
        System.out.println("userLoginInfo = " + userLoginInfo);
        int i = service.insertUserLoginInfo(userLoginInfo);
        ResponseVO responseVO = new ResponseVO();
        if (i>0) {
            System.out.println("-----debug: 用户注册成功");
            // 获取刚添加到数据库的用户登录信息
            UserLoginInfo userLoginInfoById = service.getUserLoginInfoById(userLoginInfo.getUserId());
            System.out.println("userLoginInfoById = " + userLoginInfoById);
            // 添加用户信息
            UserInfo userInfo = new UserInfo(true);
            userInfo.setUserName(userLoginInfoById.getUserPhone());
            int i1 = service.insertUserInfo(userInfo);
//            session.setAttribute("userLoginInfo",userLoginInfoById);
            session.setAttribute("user",userInfo);
            return "true";
        } else {
            System.out.println("-----debug: 用户注册失败");
            return "false";
        }

    }

    // 验证数据库有无的登录用户的信息（直接账号密码登录）
    @RequestMapping("/userLogin")
    @ResponseBody//加上 @ResponseBody 后返回结果不会被解析为跳转路径，而是直接写入 HTTP response body 中
    public String userLogin(String userName, String userPassword,HttpSession session) {

        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setUserPhone(userName);
        userLoginInfo.setUserEmail(userName);
        userLoginInfo.setPassword(userPassword);
        System.out.println("要登录的账号为:"+userLoginInfo);
        UserLoginInfo userLoginInfoByAccount = service.getUserLoginInfoByAccount(userLoginInfo);
        System.out.println("要登录的账号是否存在？："+userLoginInfoByAccount);
        if (userLoginInfoByAccount != null) {
            UserInfo userInfo = service.getUserInfoById(userLoginInfoByAccount.getUserId());
            session.setAttribute("user",userInfo);
            System.out.println("账号密码正确，前往个人主页");
            return "true";
        } else {
            return "false";
        }
    }

}
