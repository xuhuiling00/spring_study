package com.xhl.service.impl;

import com.xhl.mapper.UserMapper;
import com.xhl.pojo.Users;
import com.xhl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public Users login(String uphone, String upwd) {
//        参数非空判断
        Users u = userMapper.selectByUphoneAndUpwd(uphone,upwd);
        return u;
    }
}
