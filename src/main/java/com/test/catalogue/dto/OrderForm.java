package com.test.catalogue.dto;

import java.io.Serializable;
import java.util.List;

public class OrderForm implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3514277934340002400L;
	
	private List<OrderProductDto> productOrders;

    public List<OrderProductDto> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<OrderProductDto> productOrders) {
        this.productOrders = productOrders;
    }
}
