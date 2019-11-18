package com.fr.adaming.web.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.repository.ClientRepository;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.converter.ClientConverter;

@RestController
@RequestMapping(path = "api/hello")
public class HelloWorldController {

	@Autowired
	private ClientRepository repo;
	
	@GetMapping
	public String sayHello() {
		return "HelloWorld From Spring web";
	}

	@PostMapping
	public ClientDto create (@Valid @RequestBody ClientDto dto) {
		return ClientConverter.convertClientToClientDto(repo.save(ClientConverter.convertClientDtoToClient(dto)));
	}
	
	
	
}
