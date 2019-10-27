package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.catalogue.model.OrderProduct;
import com.test.catalogue.model.OrderProductPK;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK>{

}
