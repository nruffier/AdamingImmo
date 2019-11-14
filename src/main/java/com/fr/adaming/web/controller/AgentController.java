package com.fr.adaming.web.controller;

import java.util.List;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDtoLogin;
import com.fr.adaming.web.dto.AgentDtoRegister;

public interface AgentController {
	public String create(AgentDtoRegister agentDto);
	public String update(AgentDtoRegister agentDto);
	public String delete(Integer id);
	public List<Agent> getAll();
	public Agent findById(Integer id);
	public String login(AgentDtoLogin agentDtoLogin);
	
}
