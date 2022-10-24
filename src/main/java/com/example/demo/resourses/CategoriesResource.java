package com.example.demo.resourses;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Category;
import com.example.demo.dto.CategoryDTO;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoriesResource {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> list() {
		List<Category> list = service.list();
		List<CategoryDTO> listDto = list
			.stream()
			.map(cat -> new CategoryDTO(cat))
				.collect(Collectors.toList()
			);
		return ResponseEntity
			.ok()
			.body(listDto);
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> listPage(
		@RequestParam(value="page", defaultValue = "0") Integer page, 
		@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage, 
		@RequestParam(value="orderBy", defaultValue = "name")String orderBy, 
		@RequestParam(value="direction", defaultValue = "ASC")String direction
	) {
		Page<Category> list = service.listPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDto = list
			.map(cat -> new CategoryDTO(cat));
		return ResponseEntity
			.ok()
			.body(listDto);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Category> find (@PathVariable Integer id) {
		Category obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> store(@Valid @RequestBody CategoryDTO catDTO) {
		Category cat = service.fromDTO(catDTO);
		cat = service.store(cat);
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(cat.getId())
			.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO catDTO, @PathVariable Integer id) {
		Category cat = service.fromDTO(catDTO);
		cat.setId(id);
		cat = service.update(cat);
		return ResponseEntity
			.noContent()
			.build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity
		.noContent()
		.build();
	}
	
}
