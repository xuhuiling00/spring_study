package com.xhl;

import com.xhl.controller.UserController;
import com.xhl.mapper.UsersInter;
import com.xhl.mapper.UsersMapper;
import com.xhl.pojo.Users;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class ATMTest {
//    @Autowired
//    UserController userController;

    @Test
    public void Test1(){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入用户名；");
//        String uname = sc.next();
//
//        System.out.println("请输入密码；");
//        String pwd = sc.next();

    }

    @Test
    public void test2() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //InputStream resourceAsStream = ATMTest.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        System.out.println(sqlSessionFactory);
    }

    @Test
    public void test3(){
        UsersInter usersInter = new UsersMapper();

//        测试查
//        Users u = usersInter.getOne("xhl");

//        Users user = new Users();
//        user.setUname("hahah");
//        user.setPwd("123");
//        user.setMoney(0);

//        测试增
//        int i = usersInter.insertOne(user);

//        测试改
//        int i = usersInter.updateOne(user);

//        测试删
        int i = usersInter.deleteOne(7);
        System.out.println(i);

    }
}
