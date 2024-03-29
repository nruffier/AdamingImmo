package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Client;
import com.fr.adaming.service.impl.ClientServiceImpl;
import com.fr.adaming.web.controller.ClientController;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.ClientDtoUpdate;
import com.fr.adaming.web.dto.converter.ClientConverter;

/**
 * @author Brias Gullaume
 *
 */
@RestController
public class ClientControllerImpl implements ClientController {

	@Autowired
	private ClientServiceImpl service;

	@Override
	public String create(ClientDto dto) {
		if (service.create(ClientConverter.convertClientDtoToClient(dto)) != null) {
			return "SUCCESS CREATE CLIENT";
		} else {
			return "FAIL CREATE CLIENT";
		}
	}

	@Override
	public String update(ClientDtoUpdate dto) {
		if (service.update(ClientConverter.convertClientDtoUpdateToClient(dto)) != null) {
			return "SUCCESS UPDATE CLIENT";
		} else {
			return "FAIL UPDATE CLIENT";
		}
	}

	@Override
	public String delete(Integer id) {
		if (service.delete(id)) {
			return "SUCCESS DELETE CLIENT";
		} else {
			return "FAIL DELETE CLIENT";
		}
	}

	@Override
	public List<Client> getAll() {
		return service.getAll();
	}

	@Override
	public Client getById(Integer id) {
		return service.getById(id);
	}
	
	@Override
	public String addAgent(Integer idC, Integer idA) {
		if (service.addAgent(idC, idA)) {
			return "addAgent SUCCES";
		} else {
			return "addAgent FAIL";
		}
	}

}
