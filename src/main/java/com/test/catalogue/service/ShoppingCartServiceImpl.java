package com.test.catalogue.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.test.catalogue.model.ShoppingCart;
import com.test.catalogue.model.ShoppingStatus;
import com.test.catalogue.repository.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

	private ShoppingCartRepository shoppingCartRepository;
	
	public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepo) {
		this.shoppingCartRepository = shoppingCartRepo;
	}
	@Override
	public Iterable<ShoppingCart> getAll() {
		// TODO Auto-generated method stub
		return shoppingCartRepository.findAll();
	}

	@Override
	public ShoppingCart create(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
        return this.shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public void update(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		this.shoppingCartRepository.save(shoppingCart);
		
	}
	@Override
	public ShoppingCart getUserShoppingCart(String username) {
		// TODO Auto-generated method stub
		ShoppingCart retrieveShoppingCart = this.shoppingCartRepository.findByUsernameAndStatus(username, ShoppingStatus.CREATED.name());
		
		return (retrieveShoppingCart == null) ? new ShoppingCart() : retrieveShoppingCart;
	}

}
