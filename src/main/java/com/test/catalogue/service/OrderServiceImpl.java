package com.test.catalogue.service;

import java.time.LocalDate;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.test.catalogue.model.Order;
import com.test.catalogue.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	
	public OrderServiceImpl(OrderRepository orderRepo) {
		this.orderRepository = orderRepo;
	}
	@Override
	public Iterable<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public Order create(Order order) {
		// TODO Auto-generated method stub
		order.setDateCreated(LocalDate.now());
        return this.orderRepository.save(order);
	}

	@Override
	public void update(Order order) {
		// TODO Auto-generated method stub
		this.orderRepository.save(order);
		
	}

}
