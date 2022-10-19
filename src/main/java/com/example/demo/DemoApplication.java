package com.example.demo;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Category;
import com.example.demo.domain.City;
import com.example.demo.domain.Product;
import com.example.demo.domain.State;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.StateRepository;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category c1 = new Category(null, "Computing");
		Category c2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		State e1 = new State(null, "mg");
		State e2 = new State(null, "sp");

		City ci1 = new City(null, "uberlandia", e1);
		City ci2 = new City(null, "são paulo", e2);
		City ci3 = new City(null, "campinas", e2);
		
		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(c1));
		p2.getCategories().addAll(Arrays.asList(c1, c2));
		p3.getCategories().addAll(Arrays.asList(c1));

		e1.getCities().addAll(Arrays.asList(ci1));
		e2.getCities().addAll(Arrays.asList(ci2, ci3));
		
		categoryRepository.saveAll(Arrays.asList(c1, c2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		stateRepository.saveAll(Arrays.asList(e1, e2));
		cityRepository.saveAll(Arrays.asList(ci1, ci2, ci3));
	}
	
	

}
