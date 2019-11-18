package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private final static Logger logger = LogManager.getLogger(AgentServiceImpl.class);

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
		if (getByEmail(agent.getEmail()) != null) {
			logger.error("Agent with email " + agent.getEmail() + " already exist, create failed");
			return null;
		} else {
			logger.debug("Agent with email " + agent.getEmail() + " succefully added to the DB");
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
		if (getByEmail(agent.getEmail()) != null) {
			logger.debug("Agent with email " + agent.getEmail() + " succefully updated");
			return repo.save(agent);
		} else {
			logger.error("Agent with email " + agent.getEmail() + "doen't exist, update failed");
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
			logger.debug("Agent with id : " + id + " succefully deleted");
			repo.delete(repo.findById(id).get());
			return true;
		} else {
			logger.error("Agent with id : " + id + "doen't exist, delete failed");
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
			Agent agent = repo.findById(id).get();
			logger.debug("GetById with id : " + id + " : " + agent);
			return agent;

		} catch (NoSuchElementException e) {
			logger.error("Agent with id : " + id + " doesn't exist");
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
		try {
			Agent agent = repo.findByEmailAndPwd(email, pwd);
			logger.debug("Login succes");
			return agent;
		} catch (NoSuchElementException e) {
			logger.error("login fail");
			return null;
		}
	}

	@Override
	public Agent getByEmail(String email) {
		try {
			Agent agent = repo.findByEmail(email);
			logger.debug("GetByEmail with email : " + email + " : " + agent);
			return agent;

		} catch (NoSuchElementException e) {
			logger.error("Agent with email : " + email + " doesn't exist");
			return null;
		}
	}

}
