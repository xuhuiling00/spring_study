package com.xhl.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xhl.pojo.Category;
import com.xhl.service.CategoryService;
import com.xhl.utils.VideoJsonResult;

@RestController
@RequestMapping("/category")

public class CategoryController extends BasicController {
	
	@Autowired
	private CategoryService categoryService;

	@ApiOperation(value = "查询首页分类", notes = "类型查询接口")
	@RequestMapping("queryAll")
	public VideoJsonResult queryAll()
	{
		List<Category> list=categoryService.queryCategroyList();
		new VideoJsonResult();
		return VideoJsonResult.ok(list);
	}

	
}
