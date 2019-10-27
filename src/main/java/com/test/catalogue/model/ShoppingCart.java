package com.test.catalogue.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ShoppingCart implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1569324776695082257L;
	
	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String code;
	
	String status;

	String username;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "pk.shoppingCart")
    @Valid
    private List<ShoppingProduct> shoppingProducts = new ArrayList<>();
	
	@Transient
    public Double getTotalOrderPrice() {
        double sum = 0D;
        List<ShoppingProduct> shoppingProducts = getShoppingProducts();
        for (ShoppingProduct op : shoppingProducts) {
            sum += op.getTotalPrice();
        }
        return sum;
    }
 
    @Transient
    public int getNumberOfProducts() {
        return this.shoppingProducts.size();
    }
}
