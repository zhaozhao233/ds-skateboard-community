package com.nf.skateboard.service.user.impl;

import com.nf.skateboard.config.DaoSpringConfig;
import com.nf.skateboard.dao.UserDynamicDao;
import com.nf.skateboard.entity.UserDynamic;
import com.nf.skateboard.entity.UserDynamicImage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoSpringConfig.class)
public class UserDynamicServiceImplTest {

    @Autowired
    private UserDynamicDao dao;
    @Autowired
    private UserDynamicServiceImpl dynamicService;

    @Test
    public void insertUserDynamic() {
        UserDynamic userDynamic = new UserDynamic();
        userDynamic.setDynamicContent("今天我发表动态。。。");
        userDynamic.setUserId(1);
        userDynamic.setUserImageId(1);
        int i = dao.insertUserDynamic(userDynamic);
        System.out.println(i+"||添加结果");
    }

    @Test
    public void insertUserDynamicImage() {
        UserDynamicImage userDynamicImage = new UserDynamicImage();
        userDynamicImage.setUserImageId(1);
        userDynamicImage.setDynamicImageUrl("test1.jpg");
        int i = dao.insertUserDynamicImage(userDynamicImage);
        if (i > 0) System.out.println("添加图片成功,返回的自增ID为:"+userDynamicImage.getDynamicImageId());
    }

    @Test
    public void listUserDynamicByUserId() {
        List<UserDynamic> userDynamics = dao.listUserDynamicByUserId(1);
        for (UserDynamic userDynamic : userDynamics) {
            System.out.println(userDynamic);
        }
    }

    @Test
    public void listUserDynamicImageByUserImageId() {
        List<UserDynamicImage> userDynamicImages = dao.listUserDynamicImageByUserImageId(1);
        for (UserDynamicImage userDynamicImage : userDynamicImages) {
            System.out.println(userDynamicImage);
        }
    }


    @Test
    public void updateUserDunamicImage() {
        List<Integer> integerList = new ArrayList<Integer>();
        integerList.add(1);
//        integerList.add(2);

        int i = dao.updateUserDynamicImage(integerList);
    }



}