package com.charles.ssm.controller;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.User;
import com.charles.ssm.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("admin_user_list")
    public String list(Model model, Page page) {
        List<User> userList = userService.list(page);
        model.addAttribute("us",userList);
        model.addAttribute("page",page);
        return "admin/listUser";
    }
}
