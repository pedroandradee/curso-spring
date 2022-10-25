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
import com.example.demo.enums.UserType;
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
		Product p12 = new Product(null, "Product 12", 10.00);
		Product p13 = new Product(null, "Product 13", 10.00);
		Product p14 = new Product(null, "Product 14", 10.00);
		Product p15 = new Product(null, "Product 15", 10.00);
		Product p16 = new Product(null, "Product 16", 10.00);
		Product p17 = new Product(null, "Product 17", 10.00);
		Product p18 = new Product(null, "Product 18", 10.00);
		Product p19 = new Product(null, "Product 19", 10.00);
		Product p20 = new Product(null, "Product 20", 10.00);
		Product p21 = new Product(null, "Product 21", 10.00);
		Product p22 = new Product(null, "Product 22", 10.00);
		Product p23 = new Product(null, "Product 23", 10.00);
		Product p24 = new Product(null, "Product 24", 10.00);
		Product p25 = new Product(null, "Product 25", 10.00);
		Product p26 = new Product(null, "Product 26", 10.00);
		Product p27 = new Product(null, "Product 27", 10.00);
		Product p28 = new Product(null, "Product 28", 10.00);
		Product p29 = new Product(null, "Product 29", 10.00);
		Product p30 = new Product(null, "Product 30", 10.00);
		Product p31 = new Product(null, "Product 31", 10.00);
		Product p32 = new Product(null, "Product 32", 10.00);
		Product p33 = new Product(null, "Product 33", 10.00);
		Product p34 = new Product(null, "Product 34", 10.00);
		Product p35 = new Product(null, "Product 35", 10.00);
		Product p36 = new Product(null, "Product 36", 10.00);
		Product p37 = new Product(null, "Product 37", 10.00);
		Product p38 = new Product(null, "Product 38", 10.00);
		Product p39 = new Product(null, "Product 39", 10.00);
		Product p40 = new Product(null, "Product 40", 10.00);
		Product p41 = new Product(null, "Product 41", 10.00);
		Product p42 = new Product(null, "Product 42", 10.00);
		Product p43 = new Product(null, "Product 43", 10.00);
		Product p44 = new Product(null, "Product 44", 10.00);
		Product p45 = new Product(null, "Product 45", 10.00);
		Product p46 = new Product(null, "Product 46", 10.00);
		Product p47 = new Product(null, "Product 47", 10.00);
		Product p48 = new Product(null, "Product 48", 10.00);
		Product p49 = new Product(null, "Product 49", 10.00);
		Product p50 = new Product(null, "Product 50", 10.00);
		
		State e1 = new State(null, "Minas Gerais");
		State e2 = new State(null, "São Paulo");

		City ci1 = new City(null, "uberlandia", e1);
		City ci2 = new City(null, "são paulo", e2);
		City ci3 = new City(null, "campinas", e2);
		
		Client cl1 = new Client(null, "maria", "pedro.h8000@hotmail.com", bcrypt.encode("teste123"), "970.262.480-06", ClientType.FISICPERSON);
		Client cl2 = new Client(null, "pedro", "pedro.h8237@gmail.com", bcrypt.encode("teste123"), "495.387.570-25", ClientType.FISICPERSON);
		cl2.getContacts().addAll(Arrays.asList("99876-9876", "98765-8765"));
		cl2.setUserType(UserType.ADMIN);


		Address a1 = new Address(null, "rua 1", "123", "apt 123", "cidade 1", "12345-678", cl1, ci1);
		Address a2 = new Address(null, "rua 2", "234", "apt 234", "cidade 2", "23456-789", cl1, ci2);
		Address a3 = new Address(null, "rua 3", "345", "apt 345", "cidade 3", "34567-890", cl2, ci2);

		

		c1.getProducts().addAll(
			Arrays.asList(
				p1, p2, p3, p12, p13, p14, p15, p16, p17, p18, 
				p19, p20, p21, p22, p23, p24, p25, p26, p27, p28,
				p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48,
				p49, p50));
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
		p12.getCategories().addAll(Arrays.asList(c1));
		p13.getCategories().addAll(Arrays.asList(c1));
		p14.getCategories().addAll(Arrays.asList(c1));
		p15.getCategories().addAll(Arrays.asList(c1));
		p16.getCategories().addAll(Arrays.asList(c1));
		p17.getCategories().addAll(Arrays.asList(c1));
		p18.getCategories().addAll(Arrays.asList(c1));
		p19.getCategories().addAll(Arrays.asList(c1));
		p20.getCategories().addAll(Arrays.asList(c1));
		p21.getCategories().addAll(Arrays.asList(c1));
		p22.getCategories().addAll(Arrays.asList(c1));
		p23.getCategories().addAll(Arrays.asList(c1));
		p24.getCategories().addAll(Arrays.asList(c1));
		p25.getCategories().addAll(Arrays.asList(c1));
		p26.getCategories().addAll(Arrays.asList(c1));
		p27.getCategories().addAll(Arrays.asList(c1));
		p28.getCategories().addAll(Arrays.asList(c1));
		p29.getCategories().addAll(Arrays.asList(c1));
		p30.getCategories().addAll(Arrays.asList(c1));
		p31.getCategories().addAll(Arrays.asList(c1));
		p32.getCategories().addAll(Arrays.asList(c1));
		p33.getCategories().addAll(Arrays.asList(c1));
		p34.getCategories().addAll(Arrays.asList(c1));
		p35.getCategories().addAll(Arrays.asList(c1));
		p36.getCategories().addAll(Arrays.asList(c1));
		p37.getCategories().addAll(Arrays.asList(c1));
		p38.getCategories().addAll(Arrays.asList(c1));
		p39.getCategories().addAll(Arrays.asList(c1));
		p40.getCategories().addAll(Arrays.asList(c1));
		p41.getCategories().addAll(Arrays.asList(c1));
		p42.getCategories().addAll(Arrays.asList(c1));
		p43.getCategories().addAll(Arrays.asList(c1));
		p44.getCategories().addAll(Arrays.asList(c1));
		p45.getCategories().addAll(Arrays.asList(c1));
		p46.getCategories().addAll(Arrays.asList(c1));
		p47.getCategories().addAll(Arrays.asList(c1));
		p48.getCategories().addAll(Arrays.asList(c1));
		p49.getCategories().addAll(Arrays.asList(c1));
		p50.getCategories().addAll(Arrays.asList(c1));
		
		e1.getCities().addAll(Arrays.asList(ci1));
		e2.getCities().addAll(Arrays.asList(ci2, ci3));

		cl1.getContacts().addAll(Arrays.asList("11111-1111", "22222-2222"));

		cl1.getAdresses().addAll(Arrays.asList(a1, a2));
		cl2.getAdresses().addAll(Arrays.asList(a3));
		

		categoryRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6, c7));
		productRepository.saveAll(
			Arrays.asList(
				p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, 
				p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
				p31, p32, p33, p34, p35, p36, p37, p38, p39, p40,
				p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		stateRepository.saveAll(Arrays.asList(e1, e2));
		cityRepository.saveAll(Arrays.asList(ci1, ci2, ci3));
		clientRepository.saveAll(Arrays.asList(cl1, cl2));
		addressRepository.saveAll(Arrays.asList(a1, a2, a3));

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
