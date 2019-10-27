package com.test.catalogue.service;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.test.catalogue.model.ShoppingProduct;
import com.test.catalogue.repository.ShoppingProductRepository;

@Service
@Transactional
public class ShoppingProductServiceImpl implements ShoppingProductService{
	private ShoppingProductRepository shoppingProductRepository;
	
	public ShoppingProductServiceImpl(ShoppingProductRepository shoppingProductRepo) {
		this.shoppingProductRepository = shoppingProductRepo;
	}
	
	@Override
	public ShoppingProduct create(ShoppingProduct shoppingProduct) {
		// TODO Auto-generated method stub
		return this.shoppingProductRepository.save(shoppingProduct);
	}

	@Override
	public void delete(ShoppingProduct shoppingProduct) {
		// TODO Auto-generated method stub
		
		this.shoppingProductRepository.delete(shoppingProduct);
	}

}
