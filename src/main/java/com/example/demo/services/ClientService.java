package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Address;
import com.example.demo.domain.City;
import com.example.demo.domain.Client;
import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.ClientNewDTO;
import com.example.demo.enums.ClientType;
import com.example.demo.repositories.AddressRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.services.exceptions.DataIntegrityException;
import com.example.demo.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repo;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	public Client find(Integer id) {
		Optional<Client> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
			"Object cannot be found! Id: " + id + ", Type: " + Client.class.getName()));
	}

	@Transactional
	public Client store(Client obj) {
		obj.setId(null);
		obj = repo.save(obj);
		addressRepository.saveAll(obj.getAdresses());
		return repo.save(obj);
	}

	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("This client has orders associated!");
		}
	}

	public List<Client> list () {
		return repo.findAll();
	}
	
	public Page<Client> listPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO cDTO) {
		return new Client(cDTO.getId(), cDTO.getName(), cDTO.getEmail(), null, null, null);
	}

	public Client fromDTO(ClientNewDTO cDTO) {
		Client cli = new Client(
			null, 
			cDTO.getName(), 
			cDTO.getEmail(), 
			bcrypt.encode(cDTO.getPassword()),
			cDTO.getCPFOrCNPJ(), 
			ClientType.toEnum(cDTO.getClientType())
		);
		City cit = new City(cDTO.getCityId(), null, null);
		Address ad = new Address(
			null, 
			cDTO.getLogradouro(),
			cDTO.getNumber(),
			cDTO.getComplement(),
			cDTO.getDistrict(),
			cDTO.getZipcode(),
			cli,
			cit
		);
		cli.getAdresses().add(ad);
		cli.getContacts().add(cDTO.getPhoneNumber1());
		if (cDTO.getPhoneNumber2() != null) {
			cli.getContacts().add(cDTO.getPhoneNumber2());
		}
		if (cDTO.getPhoneNumber3() != null) {
			cli.getContacts().add(cDTO.getPhoneNumber3());
		}
		return cli;
	}

	private void updateData(Client obj1, Client obj2) {
		obj1.setName(obj2.getName());
		obj1.setEmail(obj2.getEmail());
	}
	
}
