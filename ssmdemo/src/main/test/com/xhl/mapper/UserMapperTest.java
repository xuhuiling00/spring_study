package com.xhl.mapper;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserMapperTest extends TestCase {
    @Autowired
    UserMapper userMapper;
//    测试com.xhl.mapper.UserMapper的UpdateByUname()方法
    @Test
    public void testUpdateByUname() {
        String uname = "a用户";
        int money = 100;
        int type = 1;
        userMapper.updateByUname(uname,money,type);
    }
}