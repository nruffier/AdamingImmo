package com.fr.adaming.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.ClientDtoUpdate;

@RequestMapping(path = "api/client")
public interface ClientController {

	@PostMapping(path = "/create")
	public String create(@RequestBody ClientDto dto);

	@PutMapping(path = "/update")
	public String update(@RequestBody ClientDtoUpdate dto);

	@DeleteMapping(path = "/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id);

	@GetMapping(path = "/get-all")
	public List<Client> getAll();

	@GetMapping(path = "/{id}/get-id")
	public Client getById(@PathVariable(name = "id") Integer id);

}
