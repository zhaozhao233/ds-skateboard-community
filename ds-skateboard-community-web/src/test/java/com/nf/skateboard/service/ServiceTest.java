package com.nf.skateboard.service;

import com.nf.skateboard.config.MvcConfig;
import com.nf.skateboard.dao.UserDynamicDao;
import com.nf.skateboard.entity.UserDynamic;
import com.nf.skateboard.entity.UserInfo;
import com.nf.skateboard.service.impl.UserDynamicServiceImpl;
import com.nf.skateboard.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MvcConfig.class)
public class ServiceTest {

    @Autowired
    private UserDynamicServiceImpl dynamicService;
    @Autowired
    private UserDynamicDao dao;
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void delete() {

    }
}
