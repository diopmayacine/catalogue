package com.test.catalogue.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.catalogue.model.Product;
import com.test.catalogue.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private ProductService productService;
	
	public ProductController(ProductService productServ) {
		this.productService = productServ;
	}
	@GetMapping(value = { "", "/" })
	public Iterable<Product> index() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product show(@PathVariable(value="id") int id) {
		return productService.getProduct(id);
	}
}
