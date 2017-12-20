package com.charles.ssm.controller;


import com.charles.ssm.pojo.Category;
import com.charles.ssm.pojo.Product;
import com.charles.ssm.pojo.PropertyValue;
import com.charles.ssm.service.CategoryService;
import com.charles.ssm.service.ProductService;
import com.charles.ssm.service.PropertyService;
import com.charles.ssm.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_product_editPropertyValue")
    public String list(Model model, int id) {
        List<PropertyValue> pvs = propertyValueService.listByProductId(id);
        Product p = productService.get(id);
        propertyValueService.init(p);
        Category c = categoryService.get(p.getCid());
        p.setCategory(c);
        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        return "admin/editProductValue";
    }


    @RequestMapping("admin_product_updatePropertyValue")
    @ResponseBody
    public String update(PropertyValue pv) {
        propertyValueService.update(pv);
        return "success";
    }

}
