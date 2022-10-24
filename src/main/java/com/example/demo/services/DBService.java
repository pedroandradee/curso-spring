package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

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
	@Autowired
	private BCryptPasswordEncoder bcrypt;
    
    public void instantiateDatabase() throws ParseException {
        Category c1 = new Category(null, "Computing");
		Category c2 = new Category(null, "Office");
		Category c3 = new Category(null, "Bed Table and Bathroom");
		Category c4 = new Category(null, "Eletronics");
		Category c5 = new Category(null, "gardening");
		Category c6 = new Category(null, "decoration");
		Category c7 = new Category(null, "perfumary");
		
		Product p1 = new Product(null, "Computer", 2000.00);
		Product p2 = new Product(null, "Printer", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);
		Product p4 = new Product(null, "Mesa de escritório", 300.00);
		Product p5 = new Product(null, "toalha", 50.00);
		Product p6 = new Product(null, "colcha", 200.00);
		Product p7 = new Product(null, "TV true color", 1200.00);
		Product p8 = new Product(null, "Roçadeira", 800.00);
		Product p9 = new Product(null, "Abajour", 100.00);
		Product p10 = new Product(null, "Pendente", 180.00);
		Product p11 = new Product(null, "Shampoo", 90.00);

		State e1 = new State(null, "mg");
		State e2 = new State(null, "sp");

		City ci1 = new City(null, "uberlandia", e1);
		City ci2 = new City(null, "são paulo", e2);
		City ci3 = new City(null, "campinas", e2);
		
		Client cl1 = new Client(null, "maria", "pedro.h8000@hotmail.com", bcrypt.encode("teste123"), "123.123.123-12", ClientType.FISICPERSON);
		
		Address a1 = new Address(null, "rua 1", "123", "apt 123", "cidade 1", "12345-678", cl1, ci1);
		Address a2 = new Address(null, "rua 2", "234", "apt 234", "cidade 2", "23456-789", cl1, ci2);

		

		c1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		c2.getProducts().addAll(Arrays.asList(p2, p4));
		c3.getProducts().addAll(Arrays.asList(p5, p6));
		c4.getProducts().addAll(Arrays.asList(p1, p2, p3,p7));
		c5.getProducts().addAll(Arrays.asList(p8));
		c6.getProducts().addAll(Arrays.asList(p9, p10));
		c7.getProducts().addAll(Arrays.asList(p11));
		
		p1.getCategories().addAll(Arrays.asList(c1, c4));
		p2.getCategories().addAll(Arrays.asList(c1, c2, c4));
		p3.getCategories().addAll(Arrays.asList(c1, c4));
		p4.getCategories().addAll(Arrays.asList(c2));
		p5.getCategories().addAll(Arrays.asList(c3));
		p6.getCategories().addAll(Arrays.asList(c3));
		p7.getCategories().addAll(Arrays.asList(c4));
		p8.getCategories().addAll(Arrays.asList(c5));
		p9.getCategories().addAll(Arrays.asList(c6));
		p10.getCategories().addAll(Arrays.asList(c6));
		p11.getCategories().addAll(Arrays.asList(c7));

		e1.getCities().addAll(Arrays.asList(ci1));
		e2.getCities().addAll(Arrays.asList(ci2, ci3));

		cl1.getContacts().addAll(Arrays.asList("11111-1111", "22222-2222"));

		cl1.getAdresses().addAll(Arrays.asList(a1, a2));
		

		categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
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
