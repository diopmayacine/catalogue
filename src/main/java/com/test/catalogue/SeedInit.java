package com.test.catalogue;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.test.catalogue.model.AppRole;
import com.test.catalogue.model.AppUser;
import com.test.catalogue.model.Category;
import com.test.catalogue.model.Product;
import com.test.catalogue.service.AccountService;
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
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private AccountService accountService;
	
	

	public static Double generateRandomPrice(int upperRange){
		Random random = new Random();
		return (double) ((Math.round(random.nextDouble()) * 10000) + upperRange);
	}
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		AppRole role = new AppRole();
		role.setRoleName("ADMIN");
		accountService.saveRole(role);
		role = new AppRole();	
		role.setRoleName("USER");
		accountService.saveRole(role);
		
		AppUser user = new AppUser();
		user.setUsername("admin");
		user.setPassword("1234");
		user = accountService.saveUser(user);
		
		AppUser user1 = new AppUser();
		user1.setUsername("user");
		user1.setPassword("passer");
		user1 = accountService.saveUser(user1);
		
		accountService.addRoleToUser("admin", "ADMIN");
		accountService.addRoleToUser("admin", "USER");
		
		accountService.addRoleToUser("user", "USER");
		
		
		
		
		
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
