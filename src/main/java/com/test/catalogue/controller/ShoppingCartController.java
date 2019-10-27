package com.test.catalogue.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.test.catalogue.model.Product;
import com.test.catalogue.model.ShoppingCart;
import com.test.catalogue.model.ShoppingProduct;
import com.test.catalogue.model.ShoppingStatus;
import com.test.catalogue.model.OrderProduct;
import com.test.catalogue.dto.OrderForm;
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
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	List<OrderProductDto> formDtos = form.getProductOrders();
        validateProductsExistence(formDtos);
        
  
        ShoppingCart shoppingCart = this.shoppingCartService.getUserShoppingCart(authentication.getName());
        shoppingCart.setStatus(ShoppingStatus.CREATED.name());
        shoppingCart.setUsername(authentication.getName());
        shoppingCart = this.shoppingCartService.create(shoppingCart);
        
       List<Long> oldIds= shoppingCart.getShoppingProducts().stream()
        .map(sp -> sp.getProduct().getId())
        .collect(Collectors.toList());
       List<Long> newIds = new ArrayList<>();

        List<ShoppingProduct> shoppingProducts = new ArrayList<>();
        for (OrderProductDto dto : formDtos) {
        	newIds.add(dto.getProduct().getId());
        	shoppingProducts.add(this.shoppingProductService.create(new ShoppingProduct(shoppingCart, productService.getProduct(dto
              .getProduct()
              .getId()), dto.getQuantity())));
        }
        
        List<ShoppingProduct> toBeDeletedSP = shoppingCart.getShoppingProducts().stream().filter(sp -> !newIds.contains(sp.getProduct().getId()))
                .collect (Collectors.toList());
        
        
        for (ShoppingProduct sp : toBeDeletedSP) {
        	shoppingProductService.delete(sp);
        }

        shoppingCart.setShoppingProducts(shoppingProducts);

        this.shoppingCartService.update(shoppingCart);

        String uri = ServletUriComponentsBuilder
          .fromCurrentServletMapping()
          .path("/api/shopping-carts/my-cart")
          .buildAndExpand(shoppingCart.getId())
          .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(shoppingCart, headers, HttpStatus.CREATED);
    }

    @GetMapping("/bag")
	public ShoppingCart showCart() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
   
		return shoppingCartService.getUserShoppingCart(authentication.getName());
	}
    
    @GetMapping("/validate-bag")
	public ResponseEntity<ShoppingCart> validCart() {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
   
    	ShoppingCart shoppingCart = shoppingCartService.getUserShoppingCart(authentication.getName());
    	shoppingCart.setStatus(ShoppingStatus.PAID.name());
    	
    	 this.shoppingCartService.update(shoppingCart);
    	 
    	 String uri = ServletUriComponentsBuilder
    	          .fromCurrentServletMapping()
    	          .path("/api/shopping-carts/my-cart")
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

}
