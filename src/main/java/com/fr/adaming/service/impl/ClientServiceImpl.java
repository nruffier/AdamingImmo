package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Client;
import com.fr.adaming.repository.ClientRepository;
import com.fr.adaming.service.ClientService;

/**
 * 
 * @author Brias Guillaume
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository repo;

	/**
	 * Save a given Client in the database
	 * 
	 * @param Client - the given entity
	 * @return Client if the given Client doesn't exist in the database - else,
	 *         return null
	 */
	@Override
	public Client create(Client client) {
		if (findByEmail(client.getEmail()) != null) {
			return null;
		} else {
			return repo.save(client);
		}

	}

	/**
	 * Update the values of a given Client
	 * 
	 * @param Client - the given entity
	 * @return Client if the given Client has been modified - else return null
	 */
	@Override
	public Client update(Client client) {
		if (findByEmail(client.getEmail()) != null) {
			return repo.save(client);
		} else {
			return null;
		}
	}

	/**
	 * Delete the given Client from the database
	 * 
	 * @param Integer - the id of the Client you want to delete
	 * @return true if the Client has been deleted - else return false
	 */
	@Override
	public boolean delete(Integer id) {
		if (repo.existsById(id)) {
			Client client = repo.findById(id).get();
			repo.delete(client);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Find all Clients in the database
	 * 
	 * @return List of Clients - in case no Client has been inserted, it returns an
	 *         empty list
	 */
	@Override
	public List<Client> getAll() {
		return repo.findAll();
	}

	/**
	 * Find a Client in the database with the given id
	 * 
	 * @param id - the given Long id
	 * @return the Client with the given id if it exists - else return null
	 */
	@Override
	public Client getById(Integer id) {
		if (repo.existsById(id)) {
			return repo.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public Client findByEmail(String email) {
		return repo.findByEmail(email);
	}

}
