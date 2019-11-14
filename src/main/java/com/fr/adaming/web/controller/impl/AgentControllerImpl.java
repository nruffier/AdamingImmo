package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.AgentService;
import com.fr.adaming.web.controller.AgentController;
import com.fr.adaming.web.dto.AgentDtoLogin;
import com.fr.adaming.web.dto.AgentDtoRegister;
import com.fr.adaming.web.dto.AgentDtoUpdate;
import com.fr.adaming.web.dto.converter.AgentConverter;

@RestController
public class AgentControllerImpl implements AgentController{
	@Autowired
	private AgentService service;

	@Override
	public String create(AgentDtoRegister agentDto) {
		Agent agent = AgentConverter.AgentDtoRegisterToAgent(agentDto);
		if (service.create(agent) != null) {
			return "create SUCCES";
		} else {
			return "create fail";
		}
	}

	@Override
	public String update(AgentDtoUpdate agentDto) {
		Agent agent = AgentConverter.AgentDtoUpdateToAgent(agentDto);
		if (service.update(agent) != null) {
			return "Update SUCCES";
		} else {
			return "Update FAIL";
		}
	}

	@Override
	public String delete(Integer id) {
		if (service.delete(id)) {
			return "Delete SUCCES";
		} else {
			return "Delete FAIL";
		}
	}

	@Override
	public List<Agent> getAll() {
		return service.getAll();
	}

	@Override
	public Agent findById(Integer id) {
		return service.findById(id);
	}

	@Override
	public String login(AgentDtoLogin agentDtoLogin) {
		if (service.login(agentDtoLogin.getEmail(), agentDtoLogin.getPwd()) != null) {
			return "Login SUCCES";
		} else {
			return "Login FAIL";
		}
	}
	
	
}
