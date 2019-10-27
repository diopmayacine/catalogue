package com.test.catalogue.service;

import com.test.catalogue.model.Order;

public interface OrderService {
	
	public Iterable<Order> getAllOrders();
	public Order create(Order order);
	public void update(Order order);

}
