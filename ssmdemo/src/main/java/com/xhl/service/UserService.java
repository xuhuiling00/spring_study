package com.xhl.service;

import com.xhl.pojo.Users;


public interface UserService {
//    用户登录
    Users login(String uname,String pwd);
//    用户存钱
    int setMoney(String uname,int money);
//    用户取钱
    int getMoney(String uname,int money);
//    用户转账
    int aToB(String aname,String bname,int money);
}
