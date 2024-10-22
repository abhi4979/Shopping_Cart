package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.model.Category;
import com.ecom.repository.CategoryRepository;
import com.ecom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
	private CategoryRepository categoryrepository;
	@Override
	public Category saveCategory(Category category) {
		return categoryrepository.save(category);
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryrepository.findAll();
	}

	@Override
	public Boolean existCategory(String name) {

		return categoryrepository.existsByName(name);
	}

}
