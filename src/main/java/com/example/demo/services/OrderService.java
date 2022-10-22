package com.example.demo.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.PaymentWithTicket;
import com.example.demo.domain.Product;
import com.example.demo.enums.PaymentState;
import com.example.demo.repositories.OrderItemRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.PaymentRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private ProductService productService;

	@Autowired
	private TicketService ticketService;
	
	public Order find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Object cannot be found! Id: " + id + ", Type: " + Order.class.getName()));
	}

	@Transactional
	public Order store(Order obj) {
		obj.setId(null);
		obj.setOrder_date(new Date());;
		obj.setClient(clientService.find(obj.getClient().getId()));
		obj.getPayment().setState(PaymentState.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentWithTicket) {
			PaymentWithTicket pagto = (PaymentWithTicket) obj.getPayment();
			ticketService.fillPaymentWithTicket(pagto, obj.getOrder_date());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem oi : obj.getItems()) {
			oi.setDiscount(0.0);
			oi.setProduct(productService.find(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		System.out.println(obj);
		return obj;
	}
	
}
