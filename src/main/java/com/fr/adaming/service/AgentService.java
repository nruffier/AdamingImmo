package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Agent;

public interface AgentService {

	public Agent create(Agent agent);
	
	public Agent update(Agent agent);
	
	public boolean delete(Agent agent);
	
	public List<Agent> getAll();
	
	public Agent findById(Integer id);
	
	public Agent login(String email, String pwd);
}
