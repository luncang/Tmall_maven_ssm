package com.charles.ssm.mapper;

import java.util.List;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Category;

public interface CategoryMapper {

	int add(Category category);

	void delete(int id);

	Category get(int id);

	int update(Category category);
	
	List<Category> list();

	List<Category> list(Page page);

	int total();
	
	

	
}
