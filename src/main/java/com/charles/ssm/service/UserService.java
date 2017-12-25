package com.charles.ssm.service;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.User;

import java.util.List;

public interface UserService {
    List<User> list(Page page);
    int add(User user);
    User get(int id);

}
