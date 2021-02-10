package com.xhl.mapper;

import com.xhl.pojo.Users;

import java.io.IOException;

public interface UsersInter {

    Users getOne(String uname);

    int insertOne(Users users);

    int updateOne(Users users);

    int deleteOne(Integer uid);
}
