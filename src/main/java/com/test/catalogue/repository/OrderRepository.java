package com.test.catalogue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.catalogue.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
