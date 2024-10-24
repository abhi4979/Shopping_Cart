package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecom.model.Category;
import com.ecom.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	public CategoryService service;
    @GetMapping("/")
	public String index() {
		return "admin/index";
	}
    @GetMapping("/loadproduct")
    public String loadAddProduct() {
    	return "admin/addproduct";
    }
    @GetMapping("/category")
    public String category(){
    	return "admin/category";
    }
    @PostMapping("/savecategory")
    public String saveCategory(@ModelAttribute Category category, 
                               @RequestParam("file") MultipartFile file, 
                               RedirectAttributes redirectAttributes) {
        String imageName = file != null && !file.isEmpty() ? file.getOriginalFilename() : "default.jpg";
        category.setImagename(imageName);

        // Clear previous messages
        redirectAttributes.addFlashAttribute("successMsg", null);
        redirectAttributes.addFlashAttribute("errorMsg", null);

        Boolean existCategory = service.existCategory(category.getName());
        
        // Check if category exists
        if (existCategory) {
            redirectAttributes.addFlashAttribute("errorMsg", "Category name already exists.");
            System.out.println("Category name already exists."); // Debug log
        } else {
            Category savedCategory = service.saveCategory(category);
            
            // Check if the category was saved
            if (savedCategory == null) {
                redirectAttributes.addFlashAttribute("errorMsg", "Not saved! Internal server error.");
                System.out.println("Not saved! Internal server error."); // Debug log
            } else {
                redirectAttributes.addFlashAttribute("successMsg", "Category saved successfully.");
                System.out.println("Category saved successfully."); // Debug log
            }
        }

        return "redirect:/admin/category";
    }

}
