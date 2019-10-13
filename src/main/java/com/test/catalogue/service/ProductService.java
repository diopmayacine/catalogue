package com.test.catalogue.service;

import com.test.catalogue.model.Product;

public interface ProductService {
	Iterable<Product> getAllProducts();
	Product getProduct(long id);
	Product save(Product product);
}
