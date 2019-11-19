package com.fr.adaming.web.controller.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.AgentService;
import com.fr.adaming.web.controller.AgentController;
import com.fr.adaming.web.dto.AgentDtoConnected;
import com.fr.adaming.web.dto.AgentDtoLogin;
import com.fr.adaming.web.dto.AgentDtoRegister;
import com.fr.adaming.web.dto.AgentDtoUpdate;
import com.fr.adaming.web.dto.converter.AgentConverter;

/**
 * @author Nicolas RUFFIER
 *
 */
@RestController
public class AgentControllerImpl implements AgentController {
	@Autowired
	private AgentService service;

	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private Validator validator = factory.getValidator();

	@Override
	public ResponseEntity<AgentDtoRegister> create(AgentDtoRegister agentDto) {

		Set<ConstraintViolation<AgentDtoRegister>> violations = validator.validate(agentDto);

		if (violations.isEmpty()) {
			Agent agent = service.create(AgentConverter.agentDtoRegisterToAgent(agentDto));
			if (agent != null) {
				return new ResponseEntity<AgentDtoRegister>(AgentConverter.agentToAgentDtoRegister(agent),
						HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
	}

	@Override
	public ResponseEntity<AgentDtoUpdate> update(AgentDtoUpdate agentDto) {

		Set<ConstraintViolation<AgentDtoUpdate>> violations = validator.validate(agentDto);
		if (violations.isEmpty()) {
			Agent agent = service.update(AgentConverter.agentDtoUpdateToAgent(agentDto));
			if (agent != null) {
				return new ResponseEntity<AgentDtoUpdate>(AgentConverter.agentToAgentDtoUpdate(agent), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@Override
	public boolean delete(Integer id) {
		if (service.delete(id)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Agent> getAll() {
		return service.getAll();
	}

	@Override
	public ResponseEntity<Agent> findById(Integer id) {
		Agent agent = service.getById(id);
		if (agent != null) {
			return new ResponseEntity<Agent>(agent, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<AgentDtoConnected> login(AgentDtoLogin agentDtoLogin) {
		Agent agent = service.login(agentDtoLogin.getEmail(), agentDtoLogin.getPwd());
		if (agent != null) {
			return new ResponseEntity<AgentDtoConnected>(AgentConverter.agentToAgentDtoConnected(agent), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
