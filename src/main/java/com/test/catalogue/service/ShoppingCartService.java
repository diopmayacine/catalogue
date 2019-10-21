package com.test.catalogue.service;

import com.test.catalogue.model.ShoppingCart;

public interface ShoppingCartService {
	
	public Iterable<ShoppingCart> getAll();
	public ShoppingCart create(ShoppingCart shoppingCart);
	public void update(ShoppingCart shoppingCart);

}
