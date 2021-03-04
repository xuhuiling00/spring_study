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
    //文件保存的命名空间
    public static final String File_Space="D:/IdeaProjects/spring_study/video/green_videos_dev";
    //ffmpeg所在目录
    public static final String FFMPEGEXE = "D:\\Downloads\\ffmpeg\\ffmpeg-git-full\\bin\\ffmpeg.exe";

    public static final Integer PAGE_SIZE= 5;
}
