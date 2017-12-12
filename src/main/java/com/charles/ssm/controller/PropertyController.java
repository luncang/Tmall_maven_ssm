package com.charles.ssm.controller;

import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Category;
import com.charles.ssm.pojo.Property;
import com.charles.ssm.service.CategoryService;
import com.charles.ssm.service.PropertyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_property_list")
    public String list(Model model, Page page, int cid) {

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Property> ps = propertyService.listByCategoryId(cid);
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid=" + cid);
        Category c = categoryService.get(cid);
        model.addAttribute("c", c);
        model.addAttribute("ps", ps);
        model.addAttribute("page", page);
        return "admin/listProperty";
    }

    @RequestMapping("admin_property_add")
    public String add(Model model, Property property) {
        propertyService.add(property);
        return "redirect:/admin_property_list?cid=" + property.getCid();
    }

    @RequestMapping("admin_property_delete")
    public String delete(Model model, int id) {
        Property p = propertyService.get(id);
        propertyService.delete(id);

        return "redirect:/admin_property_list?cid=" + p.getCid();
    }

    @RequestMapping("admin_property_update")
    public String update(Model model, Property property) {
        propertyService.update(property);
        return "redirect:/admin_property_list?cid=" + property.getCid();
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model, int id) {
        Property property = propertyService.get(id);
        Category c = categoryService.get(property.getCid());
        property.setCategory(c);
        model.addAttribute("p", property);
        return "admin/editProperty";
    }


}
