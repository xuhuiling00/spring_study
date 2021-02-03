package com.xhl.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceImplTest extends TestCase {
    @Autowired
    UserService userService;
    @Test
    public void aToBTest(){
        String a = "xhl";
        String b = "a用户";
        int money = 10;
        userService.aToB(a,b,money);
    }
}