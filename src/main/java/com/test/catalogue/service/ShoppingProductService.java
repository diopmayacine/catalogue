package com.test.catalogue.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.test.catalogue.model.ShoppingProduct;

@Validated
public interface ShoppingProductService {
	ShoppingProduct create(@NotNull(message = "The products for Shopping cart cannot be null.") @Valid ShoppingProduct shoppingProduct);
}
