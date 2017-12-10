package com.charles.ssm.service;

import java.util.List;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Category;

public interface CategoryService {
	List<Category> list();

	List<Category> list(Page page);

	int total();
	
	int add(Category c);
	
	void delete(int id);
	
	int update(Category c);
	
	void addTwo();
	

}
