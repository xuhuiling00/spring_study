package com.xhl.controller;

import com.xhl.controller.tools.StringEncode;
import com.xhl.pojo.Users;
import com.xhl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/portal/users/")
public class UserController {
    @Autowired
    UserService userService;

    //    用户登录
    @RequestMapping("login.action")
    public ModelAndView login(String uname, String pwd, HttpSession session) throws UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView();
        uname = StringEncode.toUTF8(uname);
        System.out.println(uname);
        Users users = userService.login(uname, pwd);
        if (users == null) {
            mav.addObject("msg", "用户登录失败请重新登录");
            mav.setViewName("index");
            return mav;
        } else {
//            保存用户信息
            session.setAttribute("users", users);

            mav.addObject("users", users);
            mav.setViewName("home");
            return mav;
        }
    }

    //    用户存钱
    @RequestMapping("setmoney.action")
    public ModelAndView setMoney(int money, HttpSession session) {
        ModelAndView mav = new ModelAndView();
//        此处可以做用户登录的验证
        Users users = (Users) session.getAttribute("users");
        int i = userService.setMoney(users.getUname(), money);

        mav.setViewName("home");
        if (i == 1) {
            mav.addObject("msg", "用户存款成功");
            users.setMoney(users.getMoney() + money);//更新当前页面金额
            mav.addObject("users", users);
            session.setAttribute("users", users);
        } else {
            mav.addObject("msg", "用户存款失败");
        }
//        mav.addObject("msg",i!=1?"用户存款失败":"用户存款成功");

        return mav;
    }

    //    //    用户取钱
//    int getMoney(String uname,int money);
    @RequestMapping("getmoney.action")
    public ModelAndView getMoney(int money, HttpSession session) {
        ModelAndView mav = new ModelAndView();
//        此处可以做用户登录的验证
        Users users = (Users) session.getAttribute("users");
        int i = userService.getMoney(users.getUname(), money);

        mav.setViewName("home");
        if (i == 1) {
            mav.addObject("msg", "用户取款成功");
            users.setMoney(users.getMoney() - money);//更新当前页面金额
            mav.addObject("users", users);
            session.setAttribute("users", users);
        } else {
            mav.addObject("msg", "用户取款失败");
        }
//        mav.addObject("msg",i!=1?"用户存款失败":"用户存款成功");
        return mav;
    }
//    //    用户转账
    @RequestMapping("atob.action")
    public ModelAndView aToB(String bname,int money,HttpSession session){
        ModelAndView mav = new ModelAndView();

        bname = StringEncode.toUTF8(bname);
        Users users = (Users) session.getAttribute("users");
        int i = userService.aToB(users.getUname(),bname,money);

        mav.setViewName("home");
        if(i==2){
            mav.addObject("msg", "用户转账成功");
            users.setMoney(users.getMoney() - money);//更新当前页面金额
            mav.addObject("users", users);
            session.setAttribute("users", users);
        }else {
            mav.addObject("msg", "用户转账失败");
        }

        return mav;
    }
}
