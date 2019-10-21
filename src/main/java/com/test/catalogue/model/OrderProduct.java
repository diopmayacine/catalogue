package com.test.catalogue.model;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class OrderProduct implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @EmbeddedId
	 @JsonIgnore
	 private OrderProductPK pk;
	 
	 @Column(nullable = false)
	 private Integer quantity;
	 
	 // default constructor
	 
	 public OrderProduct(Order order, Product product, Integer quantity) {
		 pk = new OrderProductPK();
	     pk.setOrder(order);
	     pk.setProduct(product);
	     this.quantity = quantity;
	 }
	 
	 @Transient
	 public Product getProduct() {
		 return this.pk.getProduct();
	 }
	 
	 @Transient
	 public Double getTotalPrice() {
	    return getProduct().getPrice() * getQuantity();
	 }

}
