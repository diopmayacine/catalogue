package com.test.catalogue.dto;

import java.io.Serializable;

import com.test.catalogue.model.Product;

import lombok.Data;

@Data
public class OrderProductDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2242505805115218051L;
	private Product product;
	private Integer quantity;

}
