package com.xhl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.xhl.utils.RedisOperator;

@RestController
public class BasicController {
    @Autowired
	public RedisOperator redis;
    public static final String  Filter_DEFINE="define";//默认
    public static final String  User_REDIS_SESSION="user-redis-session";

}
