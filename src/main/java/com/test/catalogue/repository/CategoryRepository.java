package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.catalogue.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
