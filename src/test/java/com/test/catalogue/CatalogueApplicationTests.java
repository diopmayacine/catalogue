package com.test.catalogue;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import com.google.common.collect.Iterables;
import com.test.catalogue.model.Product;
import com.test.catalogue.repository.ProductRepository;
import com.test.catalogue.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogueApplicationTests {

	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepository;
	
	@Test
	public void contextLoads() {
	}

	@Test
	public void testProductsList() {
		Product p = new Product();
   		p.setName("Produit 1");
   		p.setDescription("Produit 1");
   		p.setPictureUrl("http://placehold.it/200x100");
   		p.setPrice(3000.0);
   		
   		Product p1 = new Product();
   		p1.setName("Produit 2");
   		p1.setDescription("Produit 2");
   		p1.setPictureUrl("http://placehold.it/200x100");
   		p1.setPrice(4000.0);
   		Mockito.when(productRepository.findAll()).thenReturn(Stream
				.of(p,p1).collect(Collectors.toList()));
   		
		assertEquals(2, Iterables.size(productService.getAllProducts()));
	}
	
	@Test
	public  void testDetailProduct() {
		Product p = new Product();
   		p.setName("Produit 1");
   		p.setDescription("Produit 1");
   		p.setPictureUrl("http://placehold.it/200x100");
   		p.setPrice(3000.0);
   		Optional<Product> product = Optional.ofNullable(p);
   		Mockito.when(productRepository.findById(1L)).thenReturn(product);
   		
   		assertEquals("Produit 1",productService.getProduct(1L).getName());
	}
	
	@Test
	public void testAddProductToShoppingCart() {
		Product p = new Product();
   		p.setName("Produit 1");
   		p.setDescription("Produit 1");
   		p.setPictureUrl("http://placehold.it/200x100");
   		p.setPrice(3000.0);
   		
   		Optional<Product> product = Optional.ofNullable(p);
   		Mockito.when(productRepository.findById(1L)).thenReturn(product);
   		
   		
   		
	}
	
}
