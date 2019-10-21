package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.catalogue.model.ShoppingProduct;
import com.test.catalogue.model.ShpopingCartProductPK;

public interface ShoppingProductRepository extends JpaRepository<ShoppingProduct, ShpopingCartProductPK>{

}
