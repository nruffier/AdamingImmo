package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Agent;

public interface AgentService {

	public Agent create(Agent agent);
	
	public Agent update(Agent agent);
	
	public Agent delete(Agent agent);
	
	public List<Agent> listAll();
	
	public Agent findByid(Integer id);
	
	public Agent login(String email, String pwd);
	
	public boolean register(Agent agent);
}
