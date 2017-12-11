package com.charles.ssm.service.impl;

import java.util.List;

import com.charles.ssm.mapper.CategoryMapper;
import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charles.ssm.pojo.Category;
import com.charles.ssm.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryMapper categoryMapper;
	
	@Override
	public List<Category> list() {
		CategoryExample example = new CategoryExample();
		example.setOrderByClause("id desc");
		return categoryMapper.selectByExample(example);
	}

	@Override
	public int add(Category c) {
		
		return categoryMapper.insert(c);
	}

	@Override
	public void delete(int id) {
		categoryMapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public int update(Category c) {
		return categoryMapper.updateByPrimaryKey(c);
	}

	@Override
	public void addTwo() {
		 Category c1 = new Category();
	        c1.setName("短的名字");
	        categoryMapper.insert(c1);
	         
	        Category c2 = new Category();
	        c2.setName("名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下"
	        		+ ",名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下,名字长对应字段放不下");
	        categoryMapper.insert(c2);
		
	}

	@Override
	public Category get(int id) {
		return categoryMapper.selectByPrimaryKey(id);
	}


}
