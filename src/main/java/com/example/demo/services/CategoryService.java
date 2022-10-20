package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.exceptions.DataIntegrityException;
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

	public Category update(Category cat) {
		find(cat.getId());
		return repo.save(cat);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("This category has products, isn't possible to delete!");
		}
	}

	public List<Category> list () {
		return repo.findAll();
	}
	
}
