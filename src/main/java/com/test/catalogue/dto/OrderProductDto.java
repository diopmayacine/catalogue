package com.test.catalogue.dto;

import com.test.catalogue.model.Product;

import lombok.Data;

@Data
public class OrderProductDto {
	private Product product;
	private Integer quantity;

}
