package com.test.catalogue.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ShoppingProduct implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
     @JsonIgnore
	 @EmbeddedId
	 private ShpopingCartProductPK pk;
	 
	 @Column(nullable = false)
	 private Integer quantity;
	 
	 
	 // default constructor
	 
	 public ShoppingProduct(ShoppingCart shoppingCart, Product product, Integer quantity) {
		 pk = new ShpopingCartProductPK();
	     pk.setShoppingCart(shoppingCart);
	     pk.setProduct(product);
	     this.quantity = quantity;
	 }
	 
	 
	 @JsonIgnore
	 @Transient
	 public Product getProduct() {
		 return this.pk.getProduct();
	 }
	 @Transient
	 public String getProductName() {
		 return getProduct().getName();
	 }
	 
	 @Transient
	 public Long getProductId() {
		 return getProduct().getId();
	 }
	 
	 @Transient
	 public Double getTotalPrice() {
	    return getProduct().getPrice() * getQuantity();
	 }

}
