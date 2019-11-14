package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Client;
import com.fr.adaming.service.impl.ClientServiceImpl;
import com.fr.adaming.web.controller.ClientController;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.converter.ClientDtoConverter;

@RestController
@RequestMapping(path = "api/client")
public class ClientControllerImpl implements ClientController {

	@Autowired
	private ClientServiceImpl service;

	@Override
	@PostMapping(path = "/create")
	public String create(@RequestBody ClientDto dto) {
		if (service.create(ClientDtoConverter.convertClientDtoToClient(dto)) != null) {
			return "SUCCESS CREATE CLIENT";
		} else {
			return "FAIL CREATE CLIENT";
		}
	}

	@Override
	@PutMapping(path = "/update")
	public String update(@RequestBody ClientDto dto) {
		if (service.update(ClientDtoConverter.convertClientDtoToClient(dto)) != null) {
			return "SUCCESS UPDATE CLIENT";
		} else {
			return "FAIL UPDATE CLIENT";
		}
	}

	@Override
	@DeleteMapping(path = "/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id) {
		if (service.delete(id)) {
			return "SUCCESS DELETE CLIENT";
		} else {
			return "FAIL DELETE CLIENT";
		}
	}

	@Override
	@GetMapping(path = "/get-all")
	public List<Client> getAll() {
		return service.getAll();
	}

	@Override
	@GetMapping(path = "/{id}/get-id")
	public Client getById(@PathVariable(name = "id") Integer id) {
		return service.getById(id);
	}

}
