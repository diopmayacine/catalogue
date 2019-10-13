package com.test.catalogue.service;

import com.test.catalogue.exception.ResourceNotFoundException;
import com.test.catalogue.model.Category;
import com.test.catalogue.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepository categoeryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepo) {
		this.categoeryRepository = categoryRepo;
	}

	@Override
	public Iterable<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoeryRepository.findAll();
	}

	@Override
	public Category getCategory(long id) {
		// TODO Auto-generated method stub
		return categoeryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not Found"));
	}

	@Override
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		return categoeryRepository.save(category);
	}

}
