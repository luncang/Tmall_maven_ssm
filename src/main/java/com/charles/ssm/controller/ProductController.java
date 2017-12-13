package com.charles.ssm.controller;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Category;
import com.charles.ssm.pojo.Product;
import com.charles.ssm.service.CategoryService;
import com.charles.ssm.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("admin_product_list")
    public String list(Model model, int cid, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Product> ps = productService.listByCategoryId(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+cid);

        Category c = categoryService.get(cid);

        model.addAttribute("page",page);
        model.addAttribute("c",c);
        model.addAttribute("ps",ps);


        return "admin/listProduct";
    }

}
