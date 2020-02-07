package com.nf.skateboard.controller;

import com.nf.skateboard.entity.UserDynamic;
import com.nf.skateboard.entity.UserDynamicImage;
import com.nf.skateboard.entity.UserInfo;
import com.nf.skateboard.service.user.impl.UserDynamicServiceImpl;
import com.nf.skateboard.service.user.impl.UserServiceImpl;
import com.nf.skateboard.utils.UserImageFileNameUtil;
import com.nf.skateboard.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/file")
public class GetFileSourceController {
    private static final String FILE_DIRECTORY = "F:"+File.separator+"skateboard_community_project_file";
    private static final String FILE_HEAD_PORTRAOT = "head_portrait";
    private static final String FILE_OTHER_IMAGE = "other_image";
    private static final String FILE_DYNAMIC_IMAGE = "dynamic_image";

    @Autowired
    private UserServiceImpl service;
    @Autowired
    private UserDynamicServiceImpl dynamicService;

    // 读取本地文件
    @RequestMapping("/getFileSource")
    public ResponseEntity<InputStreamSource> getFileSource(String filename) throws FileNotFoundException {
        System.out.println("传过来的文件名为"+filename);
        String fileNameRegroup = fileNameRegroup(filename);
        // 在 mac 系统下 pathSeparator 值为: ,separator 值为: /
        // 加上一个标准的 "/"拼成一个绝对路径的文件名
        String fullPath = FILE_DIRECTORY + File.separator +fileNameRegroup;
        // 获取这个文件
        File file = new File(fullPath);
        //这个guessContextTypeFromName()方法是依据文件名来得到媒体类型也就是mime类型,
        // 比如常见有image/jpeg,application/json
        // 也就是知道这个文件是什么类型的文件,每种文件的节码方式是不一样的
        String medisType = URLConnection.guessContentTypeFromName(filename);
        if (medisType == null) {
            // 文件类型识别不了时,一般用来表示二进制数据
            medisType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        System.out.println("读取的文件为:"+file+"  文件的数据类型为:"+medisType);
        // 创建一个HTTP协议头
        HttpHeaders respHeaders = new HttpHeaders();
        //
        respHeaders.setContentType(MediaType.parseMediaType(medisType));
        // 输入流,显然输入流是满足不了文件下载的,需要输出流
        // 输入流变为输出流的工作 系统自动帮我们做了
        InputStreamResource isr = new InputStreamResource(
                new FileInputStream(file)
        );
        return new ResponseEntity<InputStreamSource>(isr,respHeaders, HttpStatus.OK);
    }

    // 上传头像
    @ResponseBody
    @PostMapping("/uploadImage")
    public ResponseVO uploadImage(MultipartFile userImage, HttpSession session) {
        System.out.println("上传的文件为："+userImage);
        String filename = userImage.getOriginalFilename();
        String houzui = filename.substring(filename.lastIndexOf("."));
        String relativePath = FILE_HEAD_PORTRAOT+"/"+UserImageFileNameUtil.getDataTimeForFileName()+"touxiang"+houzui;
        String absolutePath = FILE_DIRECTORY+File.separator+relativePath;
        String uploadFile = uploadFile(absolutePath,userImage);
        String code = null;
        String msg = null;
        Object data = null;
        if ("true".equalsIgnoreCase(uploadFile)) {
            code = "200";
            msg = "头像上传成功";
            UserInfo user = (UserInfo) session.getAttribute("user");    // 获取session里面的用户信息
            int i = service.updateUserInfo(new UserInfo(user.getUserId(),relativePath));    // 修改数据库的用户信息的 头像url
            if (i>0){
                System.out.println("用户头像数据库修改成功");
                UserInfo userInfoById = service.getUserInfoById(user.getUserId());
                session.setAttribute("user",userInfoById);
            }
        } else {
            code = "500";
            msg = "头像上传失败";
        }

        return ResponseVO.newBuilder().code(code).msg(msg).data(data).build();
    }

    // ？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
    // 动态的图片上传
//    @Transactional
    @RequestMapping(value = "/inDynamic",produces = "application/json")
    @ResponseBody
    public ResponseVO dynamicImageUpload(String dynamicContent,MultipartFile[] files,HttpSession session) {
        System.out.println("-----debug: 发表动态");

        System.out.println("动态的内容："+dynamicContent);
        UserDynamic userDynamic = new UserDynamic();
        UserInfo user = (UserInfo) session.getAttribute("user");
        System.out.println(user);
        userDynamic.setUserId(user.getUserId());
        userDynamic.setUserInfo(user);  //将正在登录的用户信息添加到发表动态要用到的实体类中
        userDynamic.setDynamicContent(dynamicContent);  //动态文本内容
        // 上传的图片有多张时，
//        List<Integer> autoImageId = new ArrayList<Integer>();
        // 上传的文件有多张事，先放在一起，统一添加到数据库
        List<UserDynamicImage> userDynamicImages = new ArrayList<UserDynamicImage>();
        String code = "200";
        String msg = "上传文件成功";
        Object data = null;
        // 如果有图片需要上传
        if (files !=null && files.length > 0) {
            System.out.println("有文件需要上传");
            for (MultipartFile file : files) {
                System.out.println("file = " + file);
                String filename = file.getOriginalFilename();
                String houzui = filename.substring(filename.lastIndexOf("."));
                String relativePath = FILE_DYNAMIC_IMAGE+"/"+UserImageFileNameUtil.getDataTimeForFileName()+"dynamic"+houzui;
                String absolutePath = FILE_DIRECTORY+File.separator+relativePath;
                String uploadFile = uploadFile(absolutePath, file);
                System.out.println("是否成功："+uploadFile);
                if ("false".equalsIgnoreCase(uploadFile)){
                    code = "500";
                    msg = "上传文件出错";
                    // 这里应该处理出现错误后的事
                } else {
                    // 将上传成功的用户动态的图片添加到数据库
                    UserDynamicImage userDynamicImage = new UserDynamicImage();
                    userDynamicImage.setDynamicImageUrl(relativePath);                  //发表动态的图片url
                    userDynamicImage.setDynamicImageId(userDynamic.getUserImageId());   //使用图片的id   （如果有一个不重复的数字就好办了）
                    // 将要添加到数据库的图片先用list存起来，最后统一添加
                    userDynamicImages.add(userDynamicImage);
//                    int i = dynamicService.insertUserDynamicImage(userDynamicImage);    //添加到数据库
//                    if (i > 0) {
                        // 添加成功,将刚刚自增的ID作为动态表的，发表的图片ID,添加到一个数组备用
//                        System.out.println("获得自增的图片表ID为："+userDynamicImage.getDynamicImageId());
//                        autoImageId.add(userDynamicImage.getDynamicImageId());
//                    }
                }
            }
            // 批量修改刚刚添加到数据库的发表动态图片的，用户需要用的图片ID ???
            //System.out.println("得到数组的第一个数："+autoImageId.get(0));
//            for (Integer integer : autoImageId) {
//                System.out.println(integer);
//            }
            //userDynamic.setUserImageId(autoImageId.get(0));
            // 将刚刚添加的图片的用户图片ID，统一修改
            //int i = dynamicService.updateUserDynamicImage(autoImageId);
        }

        // 将图片添加完后，向数据库插入用户发表动态信息
        // 将最终的动态信息添加到数据库
//        int i1 = dynamicService.insertUserDynamic(userDynamic);
        int i = dynamicService.insertUserDynamicAndDynamicImage(userDynamicImages, userDynamic);
        if (i>0) {
            System.out.println("-----debug: 添加一条新的动态成功");
        } else {
            code = "500";
            msg = "上传文件出错";
        }
        return ResponseVO.newBuilder().code(code).msg(msg).build();
    }

    // 文件上传
//    @RequestMapping("/upload")
    public String uploadFile(String file_path,MultipartFile myfile) {
        // 得到上传的文件，一般是要改名字的
        // 返回原始文件名，如果没有选择文件，则返回空字符串
        String fileName = myfile.getOriginalFilename();
        // 最终保存的路径
        // String path = FILE_DIRECTORY + File.separator + fileName;
        System.out.println("上传文件最终的保存路径为："+file_path);
        File file = new File(file_path);
        try {
            // 将接收到的文件传输到指定的目标文件
            // 如果目标文件已存在，将首先删除
            myfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
        System.out.println("文件上传成功");
        return "true";
    }

    // 文件下载
    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> downloadFile(String fileName) throws FileNotFoundException {
        String fullPath = FILE_DIRECTORY+File.separator+fileName;
        // 在本地找文件
        File file = new File(fullPath);
        // 根据读取出来的文件，判断该文件是什么类型的文件
        // 这个guessContextTypeFromName()方法是依据文件名来得到媒体类型也就是mime类型,
        String s = URLConnection.guessContentTypeFromName(fileName);
        if (s == null) {
            // 如果文件识别不了，就默认为二进制数据
            s=MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        // 创建一个请求头
        HttpHeaders respHeaders = new HttpHeaders();
        //
        respHeaders.setContentType(MediaType.parseMediaType(s));
        // 输入流
        InputStreamResource isr = new InputStreamResource(new FileInputStream(fullPath));
        // 自动帮我们转为输出流
        return new ResponseEntity<InputStreamResource>(isr,respHeaders,HttpStatus.OK);

    }

    public String fileNameRegroup(String myfile) {
        String myfile2 = myfile.replace("\\",File.separator);
        return myfile;
    }

}
