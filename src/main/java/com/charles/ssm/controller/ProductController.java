package com.charles.ssm.controller;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Category;
import com.charles.ssm.pojo.Product;
import com.charles.ssm.service.CategoryService;
import com.charles.ssm.service.ProductImageService;
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

    @Autowired
    private ProductImageService productImageService;

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

    @RequestMapping("admin_product_add")
    public String add(Product product){

        productService.add(product);

        return "redirect:/admin_product_list?cid="+product.getCid();
    }


    @RequestMapping("admin_product_edit")
    public String edit(Model model,int id){
        Product p = productService.get(id);
        Category c = categoryService.get(p.getCid());
        p.setCategory(c);

        model.addAttribute("p",p);
        return "admin/editProduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Model model,Product p){
        productService.update(p);
        return "redirect:/admin_product_list?cid="+p.getCid();
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id){
        Product p = productService.get(id);
        productService.delete(id);

        return "redirect:/admin_product_list?cid="+p.getCid();
    }

}
