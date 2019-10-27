package com.test.catalogue.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.catalogue.model.Category;
import com.test.catalogue.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryServ) {
		this.categoryService = categoryServ;
	}
	
	@ApiOperation(value = "lists categories", response = Category.class,
	        authorizations = { @Authorization(value = "Bearer ") })
	@GetMapping(value = { "", "/" })
	public Iterable<Category> index(){
		return categoryService.getAllCategory();
	}
	
	@GetMapping(value = "/{id}")
	public Category show(@PathVariable(value="id") int id) {
		return categoryService.getCategory(id);
	}
}
