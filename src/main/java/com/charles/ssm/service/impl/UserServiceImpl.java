package com.charles.ssm.service.impl;

import com.charles.ssm.mapper.UserMapper;
import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.User;
import com.charles.ssm.pojo.UserExample;
import com.charles.ssm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMappper;

    @Override
    public List<User> list(Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        UserExample example = new UserExample();
        example.setOrderByClause("id desc");
        List<User> users = userMappper.selectByExample(example);
        int total = (int) new PageInfo<>(users).getTotal();
        page.setTotal(total);
        return users;
    }

    @Override
    public int add(User user) {
        return userMappper.insert(user);
    }

    @Override
    public User get(int id) {
        return userMappper.selectByPrimaryKey(id);
    }
}
