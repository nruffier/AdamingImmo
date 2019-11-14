package com.fr.adaming.web.dto.converter;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDtoRegister;

public class AgentConverter {

	public static Agent AgentDtoRegisterToAgent(AgentDtoRegister agentDtoRegister) {
		return new Agent(agentDtoRegister.getEmail(), agentDtoRegister.getFullname(), agentDtoRegister.getTelephone(), agentDtoRegister.getPwd(), agentDtoRegister.getDateRectrutement());
	}
	
	public static AgentDtoRegister AgentToAgentDtoRegister(Agent agent) {
		return new AgentDtoRegister(agent.getEmail(), agent.getFullName(), agent.getTelephone(), agent.getPwd(), agent.getDateRectrutement());
		}
	
}
