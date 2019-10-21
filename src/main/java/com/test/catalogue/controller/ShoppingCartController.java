package com.test.catalogue.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.catalogue.model.Order;
import com.test.catalogue.model.OrderStatus;
import com.test.catalogue.model.ShoppingCart;
import com.test.catalogue.model.ShoppingProduct;
import com.test.catalogue.model.ShoppingStatus;
import com.test.catalogue.model.OrderProduct;
import com.test.catalogue.dto.OrderProductDto;
import com.test.catalogue.exception.ResourceNotFoundException;
import com.test.catalogue.service.OrderProductService;
import com.test.catalogue.service.OrderService;
import com.test.catalogue.service.ProductService;
import com.test.catalogue.service.ShoppingCartService;
import com.test.catalogue.service.ShoppingProductService;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

	ProductService productService;
	ShoppingCartService shoppingCartService;
    ShoppingProductService shoppingProductService;
    
    public ShoppingCartController(ProductService productService, ShoppingCartService shoppingCartService, ShoppingProductService shoppingProductService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
        this.shoppingProductService = shoppingProductService;
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<ShoppingCart> list() {
        return this.shoppingCartService.getAll();
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> create(@RequestBody OrderForm form) {
    	List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setStatus(ShoppingStatus.CREATED.name());
        shoppingCart = this.shoppingCartService.create(shoppingCart);

        List<ShoppingProduct> shoppingProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
        	shoppingProducts.add(shoppingProductService.create(new ShoppingProduct(shoppingCart, productService.getProduct(dto
              .getProduct()
              .getId()), dto.getQuantity())));
        }

        shoppingCart.setShoppingProducts(shoppingProducts);

        this.shoppingCartService.update(shoppingCart);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/shopping-carts/{id}")
          .buildAndExpand(shoppingCart.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(shoppingCart, headers, HttpStatus.CREATED);
    }

    private void validateProductsExistence(List<OrderProductDto> orderProducts) {
        List<OrderProductDto> list = orderProducts
          .stream()
          .filter(op -> Objects.isNull(productService.getProduct(op
            .getProduct()
            .getId())))
          .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            new ResourceNotFoundException("Product not found");
        }
    }

    public static class OrderForm implements Serializable{

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
}
