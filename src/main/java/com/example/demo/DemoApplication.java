package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.domain.Address;
import com.example.demo.domain.Category;
import com.example.demo.domain.City;
import com.example.demo.domain.Client;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.Payment;
import com.example.demo.domain.PaymentWithCreditCard;
import com.example.demo.domain.PaymentWithTicket;
import com.example.demo.domain.Product;
import com.example.demo.domain.State;

import com.example.demo.enums.ClientType;
import com.example.demo.enums.PaymentState;

import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.CityRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.PaymentRepository;
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
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderItemRepository orderItemRepository; 

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
		
		Client cl1 = new Client(null, "maria", "maria@email.com", "123.123.123-12", ClientType.FISICPERSON);
		
		Address a1 = new Address(null, "rua 1", "123", "apt 123", "cidade 1", "12345-678", cl1, ci1);
		Address a2 = new Address(null, "rua 2", "234", "apt 234", "cidade 2", "23456-789", cl1, ci2);

		

		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(c1));
		p2.getCategories().addAll(Arrays.asList(c1, c2));
		p3.getCategories().addAll(Arrays.asList(c1));

		e1.getCities().addAll(Arrays.asList(ci1));
		e2.getCities().addAll(Arrays.asList(ci2, ci3));

		cl1.getContacts().addAll(Arrays.asList("11111-1111", "22222-2222"));

		cl1.getAdresses().addAll(Arrays.asList(a1, a2));
		

		categoryRepository.saveAll(Arrays.asList(c1, c2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		stateRepository.saveAll(Arrays.asList(e1, e2));
		cityRepository.saveAll(Arrays.asList(ci1, ci2, ci3));
		clientRepository.saveAll(Arrays.asList(cl1));
		addressRepository.saveAll(Arrays.asList(a1, a2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order o1 = new Order(null, sdf.parse("30/09/2022 10:32"), cl1, a1);
		Order o2 = new Order(null, sdf.parse("10/10/2022 19:35"), cl1, a2);
		
		Payment pa1 = new PaymentWithCreditCard(null, PaymentState.PAYED, o1, 6);
		o1.setPayment(pa1);
		
		Payment pa2 = new PaymentWithTicket(null, PaymentState.PENDING, o2, sdf.parse("20/10/2022 00:00"), null);
		o2.setPayment(pa2);

		cl1.getOrderes().addAll(Arrays.asList(o1, o2));

		orderRepository.saveAll(Arrays.asList(o1, o2));
		paymentRepository.saveAll(Arrays.asList(pa1, pa2));

		OrderItem i1 = new OrderItem(o1, p1, 0.00, 1, 2000.00);
		OrderItem i2 = new OrderItem(o1, p3, 0.00, 2, 80.00);
		OrderItem i3 = new OrderItem(o2, p2, 100.00, 1, 800.00);

		o1.getItems().addAll(Arrays.asList(i1, i2));
		o2.getItems().addAll(Arrays.asList(i3));

		p1.getItems().addAll(Arrays.asList(i1));
		p2.getItems().addAll(Arrays.asList(i3));
		p3.getItems().addAll(Arrays.asList(i2));

		orderItemRepository.saveAll(Arrays.asList(i1, i2, i3));
	}
	
	

}
