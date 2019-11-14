package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.AgentService;
import com.fr.adaming.web.controller.AgentController;
import com.fr.adaming.web.dto.AgentDtoLogin;
import com.fr.adaming.web.dto.AgentDtoRegister;
import com.fr.adaming.web.dto.converter.AgentConverter;

@RestController
@RequestMapping(path= "api/agent")
public class AgentControllerImpl implements AgentController{
	@Autowired
	private AgentService service;

	@Override
	@PostMapping(path="/create")
	public String create(@RequestBody AgentDtoRegister agentDto) {
		Agent agent = AgentConverter.AgentDtoRegisterToAgent(agentDto);
		if (service.create(agent) != null) {
			return "create SUCCES";
		} else {
			return "create fail";
		}
	}

	@Override
	@PostMapping(path="/update")
	public String update(@RequestBody AgentDtoRegister agentDto) {
		Agent agent = AgentConverter.AgentDtoRegisterToAgent(agentDto);
		if (service.update(agent) != null) {
			return "Update SUCCES";
		} else {
			return "Update FAIL";
		}
	}

	@Override
	@DeleteMapping(path="/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id) {
		if (service.delete(id)) {
			return "Delete SUCCES";
		} else {
			return "Delete FAIL";
		}
	}

	@Override
	@GetMapping(path = "/get-all")
	public List<Agent> getAll() {
		return service.getAll();
	}

	@Override
	@GetMapping(path = "/{id}/get-id")
	public Agent findById(@PathVariable(name = "id") Integer id) {
		return service.findById(id);
	}

	@Override
	@PostMapping(path = "/login")
	public String login(AgentDtoLogin agentDtoLogin) {
		if (service.login(agentDtoLogin.getEmail(), agentDtoLogin.getPwd()) != null) {
			return "Login SUCCES";
		} else {
			return "Login FAIL";
		}
	}
	
	
}
