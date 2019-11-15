package com.fr.adaming.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDtoLogin;
import com.fr.adaming.web.dto.AgentDtoRegister;
import com.fr.adaming.web.dto.AgentDtoUpdate;
/**
 * @author Nicolas RUFFIER
 *
 */
@RequestMapping(path= "api/agent")
public interface AgentController {
	@PostMapping(path="/create")
	public String create(@RequestBody AgentDtoRegister agentDto);
	@PostMapping(path="/update")
	public String update(@RequestBody AgentDtoUpdate agentDto);
	@DeleteMapping(path="/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id);
	@GetMapping(path = "/get-all")
	public List<Agent> getAll();
	@GetMapping(path = "/{id}/get-id")
	public Agent findById(@PathVariable(name = "id") Integer id);
	@PostMapping(path = "/login")
	public String login(@RequestBody AgentDtoLogin agentDtoLogin);
	
}
