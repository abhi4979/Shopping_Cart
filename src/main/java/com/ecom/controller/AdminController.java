package com.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String savecategory(@ModelAttribute Category category,HttpSession session) {
    	if(service.existCategory(category.getName())) {
    		session.setAttribute("errorMsg", "category Name already exist");
    	}else {
     		Category savecategory=service.saveCategory(category);
     		if(ObjectUtils.isEmpty(savecategory)) {
     			session.setAttribute("errorMsg", "Not Saved! internal server error");
     		}else {
    			session.setAttribute("successMsg", "saved Successfully");
     		}
    	}
    	
    	
    	service.saveCategory(category);
    	
    	return "redirect:/category";
    }
}
