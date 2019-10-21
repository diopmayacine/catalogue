package com.test.catalogue.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.test.catalogue.exception.ResourceNotFoundException;
import com.test.catalogue.model.Product;
import com.test.catalogue.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepo){
		this.productRepository = productRepo;
	}
	@Override
	public Iterable<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found."));
		
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}

}
