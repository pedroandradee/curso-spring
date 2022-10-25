package com.example.demo.resourses;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

// import java.util.ArrayList;
// import java.util.List;

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

import com.example.demo.domain.Client;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ClientNewDTO;
import com.example.demo.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	/*@RequestMapping(method=RequestMethod.GET)
	public List<Client> list() {
		Client c1 = new Client(1, "Computing");
		Client c2 = new Client(2, "Office");
		
		List<Client> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);
		
		return list;
	}*/
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Client> find (@PathVariable Integer id) {
		Client obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<Client> find (@RequestParam(value="value") String email) {
		Client obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> list() {
		List<Client> list = service.list();
		List<ClientDTO> listDto = list
			.stream()
			.map(cat -> new ClientDTO(cat))
				.collect(Collectors.toList()
			);
		return ResponseEntity
			.ok()
			.body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> listPage(
		@RequestParam(value="page", defaultValue = "0") Integer page, 
		@RequestParam(value="linesPerPage", defaultValue = "24")Integer linesPerPage, 
		@RequestParam(value="orderBy", defaultValue = "name")String orderBy, 
		@RequestParam(value="direction", defaultValue = "ASC")String direction
	) {
		Page<Client> list = service.listPage(page, linesPerPage, orderBy, direction);
		Page<ClientDTO> listDto = list
			.map(cat -> new ClientDTO(cat));
		return ResponseEntity
			.ok()
			.body(listDto);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> store(@Valid @RequestBody ClientNewDTO clDTO) {
		Client cat = service.fromDTO(clDTO);
		cat = service.store(cat);
		URI uri = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(cat.getId())
			.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clDTO, @PathVariable Integer id) {
		Client cat = service.fromDTO(clDTO);
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
