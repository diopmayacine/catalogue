package com.test.catalogue.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.test.catalogue.model.OrderProduct;

@Validated
public interface OrderProductService {
	OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);
}
