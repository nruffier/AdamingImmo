package com.fr.adaming.web.dto.converter;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDtoRegister;
import com.fr.adaming.web.dto.AgentDtoUpdate;

/**
 * @author Nicolas RUFFIER
 *
 */
public class AgentConverter {

	public static Agent AgentDtoRegisterToAgent(AgentDtoRegister agentDtoRegister) {
		return new Agent(agentDtoRegister.getEmail(),
				agentDtoRegister.getFullName(),
				agentDtoRegister.getTelephone(),
				agentDtoRegister.getPwd(),
				agentDtoRegister.getDateRectrutement()
				);
	}

	public static AgentDtoRegister AgentToAgentDtoRegister(Agent agent) {
		return new AgentDtoRegister(agent.getEmail(),
				agent.getFullName(),
				agent.getTelephone(),
				agent.getPwd(),
				agent.getDateRectrutement());
	}

	public static Agent AgentDtoUpdateToAgent(AgentDtoUpdate agentDtoUpdate) {
		return new Agent(
				agentDtoUpdate.getId(),
				agentDtoUpdate.getEmail(),
				agentDtoUpdate.getFullName(),
				agentDtoUpdate.getTelephone(),
				agentDtoUpdate.getPwd(),
				agentDtoUpdate.getDateRectrutement()
				);
	}
	
	public static AgentDtoUpdate AgentToAgentDtoUpdate(Agent agent) {
		return new AgentDtoUpdate(
				agent.getId(),
				agent.getEmail(),
				agent.getFullName(),
				agent.getTelephone(),
				agent.getPwd(),
				agent.getDateRectrutement());
	}
}
