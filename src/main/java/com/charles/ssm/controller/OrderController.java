package com.charles.ssm.controller;

import com.charles.ssm.page.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class OrderController {

    @RequestMapping("admin_order_list")
    public String list(Model model, Page page){

        return "admin/listOrder";
    }
}
