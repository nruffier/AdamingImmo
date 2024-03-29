package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Agent;

/**
 * @author Nicolas RUFFIER
 *
 */
public interface AgentService {

	public Agent create(Agent agent);

	public Agent update(Agent agent);

	public boolean delete(Integer id);

	public List<Agent> getAll();

	public Agent getById(Integer id);

	public Agent login(String email, String pwd);

	public Agent findByEmail(String email);
}
