package com.fr.adaming.web.dto.converter;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDtoConnected;
import com.fr.adaming.web.dto.AgentDtoRegister;
import com.fr.adaming.web.dto.AgentDtoUpdate;

/**
 * @author Nicolas RUFFIER
 *
 */
public class AgentConverter {

	public static Agent agentDtoRegisterToAgent(AgentDtoRegister agentDtoRegister) {
		if (agentDtoRegister == null) {
			return null;
		} else {
			return new Agent(agentDtoRegister.getEmail(), agentDtoRegister.getFullName(),
					agentDtoRegister.getTelephone(), agentDtoRegister.getPwd(), agentDtoRegister.getDateRectrutement());
		}
	}

	public static AgentDtoRegister agentToAgentDtoRegister(Agent agent) {
		if (agent == null) {
			return null;
		} else {
			return new AgentDtoRegister(agent.getEmail(), agent.getFullName(), agent.getTelephone(), agent.getPwd(),
					agent.getDateRectrutement());
		}

	}

	public static Agent agentDtoUpdateToAgent(AgentDtoUpdate agentDtoUpdate) {
		if (agentDtoUpdate == null) {
			return null;
		} else {
			return new Agent(agentDtoUpdate.getId(), agentDtoUpdate.getEmail(), agentDtoUpdate.getFullName(),
					agentDtoUpdate.getTelephone(), agentDtoUpdate.getPwd(), agentDtoUpdate.getDateRectrutement());
		}
	}

	public static AgentDtoUpdate agentToAgentDtoUpdate(Agent agent) {
		if (agent == null) {
			return null;
		} else {
			return new AgentDtoUpdate(agent.getId(), agent.getEmail(), agent.getFullName(), agent.getTelephone(),
					agent.getPwd(), agent.getDateRectrutement());
		}
	}

	public static AgentDtoConnected agentToAgentDtoConnected(Agent agent) {
		if (agent == null) {
			return null;
		} else {
			return new AgentDtoConnected(agent.getId(), agent.getEmail(), agent.getFullName(), agent.getTelephone(),
					agent.getDateRectrutement());
		}
	}
}
