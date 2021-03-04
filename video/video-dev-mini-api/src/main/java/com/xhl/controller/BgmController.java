package com.xhl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhl.service.BgmService;
import com.xhl.utils.VideoJsonResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/bgm")
@Api(value = "背景音乐接口", tags = { "背景音乐业务controller" })
public class BgmController {
	@Autowired
	private BgmService bgmService;
	@ApiOperation(value = "列表", notes = "获取背景音乐列表")
	@PostMapping("/list")
	public VideoJsonResult list() {
		return VideoJsonResult.ok(bgmService.queryBgmList());
	}

	@PostMapping("/listVideoCategory")
	public VideoJsonResult listVideoCategory() {
		
		return VideoJsonResult.ok(bgmService.queryBgmList());
	}
	
}
