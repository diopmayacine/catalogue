package com.test.catalogue.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.test.catalogue.exception.ResourceNotFoundException;
import com.test.catalogue.model.Category;
import com.test.catalogue.repository.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepo) {
		this.categoryRepository = categoryRepo;
	}

	@Override
	public Iterable<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategory(long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not Found"));
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

}
