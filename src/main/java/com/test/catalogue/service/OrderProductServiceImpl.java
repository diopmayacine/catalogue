package com.test.catalogue.service;


import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.test.catalogue.model.OrderProduct;
import com.test.catalogue.repository.OrderProductRepository;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService{
	private OrderProductRepository orderProductRepository;
	
	public OrderProductServiceImpl(OrderProductRepository orderProductRepo) {
		this.orderProductRepository = orderProductRepo;
	}
	
	@Override
	public OrderProduct create(OrderProduct orderProduct) {
		// TODO Auto-generated method stub
		return this.orderProductRepository.save(orderProduct);
	}

}
