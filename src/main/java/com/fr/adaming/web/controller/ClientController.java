package com.fr.adaming.web.controller;

import java.util.List;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientDto;

public interface ClientController {

	public String create(ClientDto dto);

	public String update(ClientDto dto);

	public String delete(Integer id);

	public List<Client> getAll();

	public Client getById(Integer id);

}
