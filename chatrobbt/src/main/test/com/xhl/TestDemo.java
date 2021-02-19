package com.xhl;

import com.xhl.controller.IndexController;
import com.xhl.mapper.IndexMapper;
import com.xhl.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TestDemo {

    @Autowired
    IndexMapper indexMapper;

    @Test
    public void test1(){
        SqlSessionFactory sqlSessionFactory = SqlSessionUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
    }

    @Test
    public void test2(){
//        IndexController indexController = new IndexController();
//        String defaultReply = indexController.getDefaultRaply();
//        System.out.println(defaultReply);
    }

    @Test
    public void test3(){
        System.out.println(indexMapper.getDefaultReply());
    }
}
