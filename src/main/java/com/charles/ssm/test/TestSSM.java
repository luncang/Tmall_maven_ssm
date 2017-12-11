package com.charles.ssm.test;

import com.charles.ssm.mapper.CategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.charles.ssm.pojo.Category;
import com.charles.ssm.service.CategoryService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSSM {
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private CategoryService categoryService;
	
	@Test
	public void testAdd(){
		Category c = new Category();
		c.setName("new category");
		categoryMapper.insert(c);
	}
	
	@Test
	public void testList() {
		
	}
	
	@Test
	public void testTotal(){
//		categoryMapper.total();
	}
	
	@Test
	public void testTwo(){
		categoryService.addTwo();
	}


	public static void main(String[] args){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (
				Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall?useUnicode=true&characterEncoding=utf8",
						"root", "root");
				Statement s = c.createStatement();
		)
		{
			for (int i = 1; i <=10 ; i++) {
				String sqlFormat = "insert into category values (null, '测试分类%d')";
				String sql = String.format(sqlFormat, i);
				s.execute(sql);
			}

			System.out.println("已经成功创建10条分类测试数据");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	
	

}
