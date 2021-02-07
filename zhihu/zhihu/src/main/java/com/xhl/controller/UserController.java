package com.xhl.controller;

import com.xhl.pojo.Users;
import com.xhl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/portal/user/")
public class UserController {
//    注入业务层
    @Autowired
UserService userService;

    @RequestMapping("login.do")
    public String login(String uphone , String upwd, HttpSession session){
//        登录成功保存数据到session中，方便后续使用
        Users u = userService.login(uphone,upwd);

        System.out.println(u);
        if(u!=null){
            session.setAttribute("user",u);
            return "登录成功";
        }else {
            return "登录失败";
        }


    }

}
