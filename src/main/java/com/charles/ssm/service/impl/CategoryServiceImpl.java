package com.charles.ssm.service.impl;

import java.util.List;

import com.charles.ssm.mapper.CategoryMapper;
import com.charles.ssm.page.Page;
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
		return categoryMapper.list();
	}

	@Override
	public List<Category> list(Page page) {
		return categoryMapper.list(page);
	}

	@Override
	public int total() {
		return categoryMapper.total();
	}

	@Override
	public int add(Category c) {
		
		return categoryMapper.add(c);
	}

	@Override
	public void delete(int id) {
		categoryMapper.delete(id);
		
	}

	@Override
	public int update(Category c) {
		return categoryMapper.update(c);
	}

	@Override
	public void addTwo() {
		 Category c1 = new Category();
	        c1.setName("短的名字");
	        categoryMapper.add(c1);
	         
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
	        categoryMapper.add(c2);
		
	}

	

}
