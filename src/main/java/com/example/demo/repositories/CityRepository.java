package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
	
}
