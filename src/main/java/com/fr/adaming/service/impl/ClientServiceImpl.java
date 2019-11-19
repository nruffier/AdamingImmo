package com.fr.adaming.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Client;
import com.fr.adaming.repository.AgentRepository;
import com.fr.adaming.repository.ClientRepository;
import com.fr.adaming.service.ClientService;

/**
 * 
 * @author Brias Guillaume
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

	private final static Logger logger = LogManager.getLogger(AgentServiceImpl.class);

	@Autowired
	private ClientRepository repo;

	@Autowired
	private AgentRepository repoA;

	/**
	 * Save a given Client in the database
	 * 
	 * @param client - the given entity
	 * @return Client if the given Client doesn't exist in the database - else,
	 *         return null
	 */
	@Override
	public Client create(Client client) {
		if (getByEmail(client.getEmail()) != null) {
			logger.error("Client with email " + client.getEmail() + " already exist, create failed");
			return null;
		} else {
			logger.debug("Client with email " + client.getEmail() + " successfully added to the DB");
			return repo.save(client);
		}

	}

	/**
	 * Update the values of a given Client
	 * 
	 * @param client - the given entity
	 * @return Client if the given Client has been modified - else return null
	 */
	@Override
	public Client update(Client client) {
		if (getByEmail(client.getEmail()) != null) {
			logger.debug("Client with email " + client.getEmail() + " successfully updated");
			return repo.save(client);
		} else {
			logger.error("Client with email " + client.getEmail() + " doen't exist, update failed");
			return null;
		}
	}

	/**
	 * Delete the given Client from the database
	 * 
	 * @param id - the id of the Client you want to delete
	 * @return true if the Client has been deleted - else return false
	 */
	@Override
	public boolean delete(Integer id) {
		if (repo.existsById(id)) {
			logger.debug("Client with id : " + id + " successfully deleted");
			Client client = repo.findById(id).get();
			repo.delete(client);
			return true;
		} else {
			logger.error("Client with id : " + id + " doen't exist, delete failed");
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
	 * @param id - the given Integer id
	 * @return the Client with the given id if it exists - else return null
	 */
	@Override
	public Client getById(Integer id) {
		if (repo.existsById(id)) {
			Client client = repo.findById(id).get();
			logger.debug("GetById with id : " + id + " : " + client);
			return client;
		} else {
			logger.error("Client with id : " + id + " doesn't exist");
			return null;
		}
	}

	/**
	 * Find a Client in the database with the given email
	 * 
	 * @param email - the given email
	 * @return the Client with the given email if it exists - else return null
	 */
	@Override
	public Client getByEmail(String email) {
		return repo.findByEmail(email);
	}

	/**
	 * Add the Agent with the given id to the Client with the given id
	 * 
	 * @param idC - the given Integer id of the Client,
	 * @param idA - the given Integer id of the Agent
	 * @return true if the Agent as been added to the Client - else return false
	 */
	@Override
	public boolean addAgent(Integer idC, Integer idA) {
		if (repo.existsById(idC) && repoA.existsById(idA)) {
			logger.debug("Agent with id " + idA + " successfully added to client with id " + idC);
			repo.addAgent(idC, idA);
			return true;
		} else {
			logger.error("Agent with id " + idA + " and/or client with id " + idC + " don't exist");
			return false;
		}
	}

}
