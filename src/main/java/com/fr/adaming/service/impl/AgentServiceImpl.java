package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.repository.AgentRepository;
import com.fr.adaming.service.AgentService;

/**
 * @author Nicolas RUFFIER
 *
 */
@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	private AgentRepository repo;

	/**
	 * Save a givent agent in the database
	 *
	 * @param agent - the givent entity
	 * @return updated agent if the given entity is not already in the database Null
	 *         if the given entity exist.
	 *
	 */
	@Override
	public Agent create(Agent agent) {
		if (findByEmail(agent.getEmail()) != null) {
			return null;
		} else {
			return repo.save(agent);
		}
	}

	/**
	 * Modify the given entity by Id
	 * 
	 * @param agent - the given entity (must have an id)
	 * @return the update entity is the given entity is in the database and the
	 *         update is done - null if the given entity is not present in the DB
	 */
	@Override
	public Agent update(Agent agent) {
		if (findByEmail(agent.getEmail()) != null) {
			return repo.save(agent);
		} else {
			return null;
		}
	}

	/**
	 * Delete the given entity from the database
	 * 
	 * @param agent - the given entity
	 * @return true if the given entity exist in the DB - false if the given entity
	 *         doesen't exist.
	 */
	@Override
	public boolean delete(Integer id) {
		if (repo.existsById(id)) {
			repo.delete(repo.findById(id).get());
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Find an Agent with a given Id
	 *
	 * @param Integer id - the given id
	 * @return Agent with the given id if present in the DB - null if not
	 */
	@Override
	public Agent getById(Integer id) {
		try {
			return repo.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * List all the Agent in the database
	 * 
	 * @return List<Agent>
	 */
	@Override
	public List<Agent> getAll() {
		return repo.findAll();
	}

	/**
	 * Check the database for a combinaison of email and password
	 * 
	 * @param String email, String pwd - the given email and pwd
	 * @return Agent if exist - null if not
	 *
	 */
	@Override
	public Agent login(String email, String pwd) {
		return repo.findByEmailAndPwd(email, pwd);
	}

	@Override
	public Agent findByEmail(String email) {
		return repo.findByEmail(email);
	}

}
