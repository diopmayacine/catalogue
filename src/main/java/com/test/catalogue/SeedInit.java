package com.test.catalogue;

import java.util.Random;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.test.catalogue.model.Category;
import com.test.catalogue.model.Product;
import com.test.catalogue.service.CategoryService;
import com.test.catalogue.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@AllArgsConstructor
public class SeedInit implements ApplicationRunner {
	private CategoryService categoryService;
	private ProductService productService;
	
	

public static Double generateRandomPrice(int upperRange){
    Random random = new Random();
    return (double) ((Math.round(random.nextDouble()) * 10000) + upperRange);
}
	
	

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		for(int j=1; j<=3; j++) {
			Category c = new Category();
			c.setName("Categorie "+j);
			c.setDescription("Categorie "+j);
			for (int i = 1 ; i<=20; i++) {
				
		   		
		   		Product p = new Product();
		   		p.setName("Produit "+i);
		   		p.setDescription("Produit "+i);
		   		p.setPictureUrl("http://placehold.it/200x100");
		   		p.setPrice(generateRandomPrice(3000));
		   		p.setCategory(c);
		   		
		   		categoryService.save(c);	
		   		productService.save(p);
		 
			}
		
		}
			
	}

}
