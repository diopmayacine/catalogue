package com.test.catalogue;

import javax.persistence.ManyToOne;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.catalogue.model.Category;
import com.test.catalogue.model.Product;
import com.test.catalogue.service.CategoryService;
import com.test.catalogue.service.ProductService;

@SpringBootApplication
public class CatalogueApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogueApplication.class, args);
	}

}
