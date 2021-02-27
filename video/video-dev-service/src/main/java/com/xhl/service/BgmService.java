package com.xhl.service;

import java.util.List;

import com.xhl.pojo.Bgm;

public interface BgmService 
{
    //查询bgm列表
	List<Bgm> queryBgmList();
    //查询单个bgmid
    public Bgm queryBgmById(String bgmId);


}
