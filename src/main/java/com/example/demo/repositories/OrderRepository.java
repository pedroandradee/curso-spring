package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	
}
