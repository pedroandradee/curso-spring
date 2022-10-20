package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	
	public Category find(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Object cannot be found! Id: " + id + ", Type: " + Category.class.getName()));
	}

	public Category store(Category cat) {
		cat.setId(null);
		return repo.save(cat);
	}
	
}
