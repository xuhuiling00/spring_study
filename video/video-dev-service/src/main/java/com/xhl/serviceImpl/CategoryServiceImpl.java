package com.xhl.serviceImpl;

import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xhl.mapper.BgmMapper;
import com.xhl.mapper.CategoryMapper;
import com.xhl.pojo.Bgm;
import com.xhl.pojo.Category;
import com.xhl.service.BgmService;
import com.xhl.service.CategoryService;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryMapper categoryMapper;
	
	@Override
	public List<Category> queryCategroyList() {
		return	categoryMapper.selectAll();
	}


}
