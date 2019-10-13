package com.test.catalogue.service;

import com.test.catalogue.model.Category;

public interface CategoryService {

	Iterable<Category> getAllCategory();
	Category getCategory(long id);
	Category saveCategory(Category category);
}
