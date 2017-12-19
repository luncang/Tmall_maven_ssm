package com.charles.ssm.controller;

import com.charles.ssm.image.UploadImageFile;
import com.charles.ssm.pojo.Category;
import com.charles.ssm.pojo.Product;
import com.charles.ssm.pojo.ProductImage;
import com.charles.ssm.service.CategoryService;
import com.charles.ssm.service.ProductImageService;
import com.charles.ssm.service.ProductService;
import com.charles.ssm.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("admin_productImage_list")
    public String list(Model model, int pid) {
        Product p = productService.get(pid);
        Category c = categoryService.get(p.getCid());
        List<ProductImage> single = productImageService.list(pid, ProductImageService.TYPE_SINGLE);
        List<ProductImage> details = productImageService.list(pid, ProductImageService.TYPE_DETAIL);
        p.setProductSingleImages(single);
        p.setProductDetailImages(details);
        p.setCategory(c);
        model.addAttribute("p", p);
        model.addAttribute("pisSingle", p.getProductSingleImages());
        model.addAttribute("pisDetail", p.getProductDetailImages());
        return "admin/listProductImage";
    }

    //TODO--上传图片有空指针，需要排错
    @RequestMapping("admin_productImage_add")
    public String add(ProductImage pi, HttpSession session, UploadImageFile uploadedImageFile) {
        productImageService.insert(pi);

        String fileName = pi.getId() + ".jpg";
        String imageFolder=null;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.TYPE_SINGLE.equals(pi.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
        } else if(ProductImageService.TYPE_DETAIL.equals(pi.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
        }


        File f = new File(imageFolder, fileName);
        f.getParentFile().mkdirs();
        try {
            System.out.println((uploadedImageFile.getImage() == null));
            uploadedImageFile.getImage().transferTo(f);
            BufferedImage img = ImageUtil.change2jpg(f);
            ImageIO.write(img, "jpg", f);

            if (ProductImageService.TYPE_SINGLE.equals(pi.getType())) {
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);

                ImageUtil.resizeImage(f, 56, 56, f_small);
                ImageUtil.resizeImage(f, 217, 190, f_middle);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin_productImage_list?pid=" + pi.getPid();
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(HttpSession session, int id) {
        ProductImage productImage = productImageService.get(id);
        String fileName = id + ".jpg";
        String singleFolder;
        String singleMiddleFolder, singleSmallFolder, detailFolder;


        if (ProductImageService.TYPE_SINGLE.equals(productImage.getType())) {
            singleFolder = session.getServletContext().getRealPath("img/productSingle");
            singleMiddleFolder = session.getServletContext().getRealPath("img/productSingle_middle");
            singleSmallFolder = session.getServletContext().getRealPath("img/productSingle_small");

            File single = new File(singleFolder, fileName);
            if (single.exists()) {
                single.delete();
            }
            File small = new File(singleSmallFolder, fileName);
            if (small.exists()) {
                small.delete();
            }
            File middle = new File(singleMiddleFolder, fileName);
            if (middle.exists())
                middle.delete();

        } else if (ProductImageService.TYPE_DETAIL.equals(productImage.getType())) {
            detailFolder = session.getServletContext().getRealPath("img/productDetail");
            File detail = new File(detailFolder, fileName);
            if (detail.exists())
                detail.delete();
        }
        productImageService.delete(id);
        return "redirect:/admin_productImage_list?pid=" + productImage.getPid();

    }

}
