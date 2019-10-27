package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.catalogue.model.ShoppingCart;
import com.test.catalogue.model.ShoppingStatus;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	public ShoppingCart findByUsernameAndStatus(String username, String status);
}
