package com.charles.ssm.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.charles.ssm.image.UploadImageFile;
import com.charles.ssm.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.charles.ssm.page.Page;
import com.charles.ssm.pojo.Category;
import com.charles.ssm.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

//	@RequestMapping("/listCategory")
//	public ModelAndView listCategory(Page page) {
//		System.out.println("listCategory....");
//		ModelAndView mav = new ModelAndView();
//		PageHelper.offsetPage(page.getStart(), page.getCount());
//		List<Category> cs = categoryService.list();
//		int total = (int)new PageInfo<>(cs).getTotal();
//		page.caculateLast(total);
//
//		// 放入转发参数
//		mav.addObject("cs", cs);
//		// 放入jsp路径
//		mav.setViewName("listCategory");
//		return mav;
//	}
//
//	@RequestMapping("/addCategory")
//	public ModelAndView addCategory(Category c){
//
//		int add = categoryService.add(c);
//
//		ModelAndView mav = new ModelAndView();
//		// 放入jsp路径
//		mav.setViewName("redirect:/listCategory");
//		return mav;
//	}
//
//
//	@RequestMapping("/deleteCategory")
//	public ModelAndView deleteCategory(int  id){
//
//		categoryService.delete(id);
//
//		ModelAndView mav = new ModelAndView();
//		// 放入jsp路径
//		mav.setViewName("redirect:/listCategory");
//		return mav;
//	}
//
//
//
//	@RequestMapping("/editCategory")
//	public ModelAndView editCategory(Category  c){
//		ModelAndView mav = new ModelAndView();
//		// 放入jsp路径
//		mav.setViewName("editCategory");
//		mav.addObject("c",c);
//		return mav;
//	}
//
//
//
//	@RequestMapping("/updateCategory")
//	public ModelAndView updateCategory(Category  c){
//		int update = categoryService.update(c);
//		System.out.println("update:"+update);
//		ModelAndView mav = new ModelAndView();
//		// 放入jsp路径
//		mav.setViewName("redirect:/listCategory");
//		return mav;
//	}
//
//	@ResponseBody
//	@RequestMapping("/getOneCategory")
//	public String getOneCategory(){
//		Category c = new Category();
//		c.setId(100);
//		c.setName("第100个分类");
//		JSONObject json = new JSONObject();
//		json.put("category", JSONObject.toJSON(c));
//		return json.toJSONString();
//	}
//
//	@ResponseBody
//	@RequestMapping("/getManyCategory")
//	public String getManyCategory(){
//
//		List<Category> cs = new ArrayList<>();
//		for(int i=0;i<10;i++){
//			Category c = new Category();
//			c.setId(i);
//			c.setName("分类名称："+i);
//			cs.add(c);
//		}
//		return JSONObject.toJSON(cs).toString();
//	}
//
//	@ResponseBody
//	@RequestMapping("/submitCategory")
//	public String submitCategory(@RequestBody Category c){
//		System.out.println("ssm接受浏览器提交的json,并转换为category对象："+c);
//		return "ok";
//	}


    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Category> cs = categoryService.list();
        int total = (int)new PageInfo<>(cs).getTotal();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadImageFile uploadImageFile) throws IOException {
        categoryService.add(c);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId() + ".jpg");
        System.out.println("path:" + file.getAbsolutePath());
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        uploadImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id,HttpSession session) throws IOException {
        categoryService.delete(id);

        File  imageFolder= new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(Model model,int id){
        Category c = categoryService.get(id);
        model.addAttribute("c",c);

        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category category,HttpSession session, UploadImageFile uploadImageFile) throws IOException {
        categoryService.update(category);

        if(uploadImageFile.getImage()!=null&&!uploadImageFile.getImage().isEmpty()){
            File folder = new File( session.getServletContext().getRealPath("img/category"));
            File file = new File(folder,category.getId()+".jpg");

            if(!file.getParentFile().exists()){
                folder.getParentFile().mkdirs();
            }
            uploadImageFile.getImage().transferTo(file);
            BufferedImage image = ImageUtil.change2jpg(file);
            ImageIO.write(image,"jpg",file);
        }

        return "redirect:/admin_category_list";
    }


}
