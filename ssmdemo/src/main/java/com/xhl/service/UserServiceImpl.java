package com.xhl.service;

import com.xhl.mapper.UserMapper;
import com.xhl.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public Users login(String uname, String pwd) {
        if(StringUtils.isEmpty(uname)||StringUtils.isEmpty(pwd)){
            return null;
        }
        Users u = userMapper.selectByUnameAndPwd(uname,pwd);
        return u;
    }
    //    用户存钱
    @Override
    public int setMoney(String uname, int money) {
//        参数验证
        if(money <=0 ){
            return 0;
        }
        int i = userMapper.updateByUname(uname, money, 0);
        return i;
    }
    //    用户取钱
    @Override
    public int getMoney(String uname, int money) {
        //        参数验证
        if(money <=0 ){
            return 0;
        }
        int i = userMapper.updateByUname(uname, money, 1);
        return i;
    }
    //    用户转账
    @Override
    public int aToB(String aname, String bname, int money) {
        //        参数验证
        if(money <=0 ){
            return 0;
        }
        int a= userMapper.updateByUname(aname,money,1);
        int b= userMapper.updateByUname(bname,money,0);
        return a+b;
    }
}
