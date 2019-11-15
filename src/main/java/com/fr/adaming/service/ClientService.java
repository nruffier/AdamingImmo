package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Client;

public interface ClientService {
	
	public Client create(Client client);
	
	public Client update(Client client);
	
	public boolean delete(Integer id);
	
	public List<Client> getAll();
	
	public Client getById(Integer id);
	
	public Client findByEmail(String email);
	
}
