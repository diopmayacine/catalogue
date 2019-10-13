package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.catalogue.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
