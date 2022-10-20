package com.example.demo.resourses;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Category;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoriesResource {
	
	@Autowired
	private CategoryService service;
	
	/*@RequestMapping(method=RequestMethod.GET)
	public List<Category> list() {
		Category c1 = new Category(1, "Computing");
		Category c2 = new Category(2, "Office");
		
		List<Category> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		
		return list;
	}*/
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find (@PathVariable Integer id) {
		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> store(@RequestBody Category cat) {
		cat = service.store(cat);
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(cat.getId())
			.toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
