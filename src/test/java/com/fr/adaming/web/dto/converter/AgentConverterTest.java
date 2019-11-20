package com.fr.adaming.web.dto.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDtoConnected;
import com.fr.adaming.web.dto.AgentDtoRegister;
import com.fr.adaming.web.dto.AgentDtoUpdate;

public class AgentConverterTest {

	
	
	@Test
	public void ConvertValidAgentDtoRegisterToAgent() {
		AgentDtoRegister dto = new AgentDtoRegister("email@gmail.com", "fullname", "0123456789", "azertyuiop", LocalDate.of(2019, 11, 20));
		Agent agent = AgentConverter.agentDtoRegisterToAgent(dto);
		
		assertNotNull(agent);
		assertNotNull(agent.getEmail());
		assertNotNull(agent.getFullName());
		assertNotNull(agent.getTelephone());
		assertNotNull(agent.getPwd());
		assertNotNull(agent.getDateRectrutement());
		assertSame(agent.getEmail(), "email@gmail.com");
		assertSame(agent.getFullName(), "fullname");
		assertSame(agent.getTelephone(), "0123456789");
		assertSame(agent.getPwd(), "azertyuiop");
		assertEquals(agent.getDateRectrutement(), LocalDate.of(2019, 11, 20));
	}
	@Test
	public void ConvertAgentDtoRegisterNullToAgent() {
		AgentDtoRegister dto = null;
		Agent agent = AgentConverter.agentDtoRegisterToAgent(dto);
		assertNull(agent);
	}
	
	@Test
	public void ConvertValidAgentToAgentDtoRegister() {
		Agent agent = new Agent("email@gmail.com", "fullname", "0123456789", "azertyuiop", LocalDate.of(2019, 11, 20));
		AgentDtoRegister dto = AgentConverter.agentToAgentDtoRegister(agent);
		assertNotNull(dto);
		assertNotNull(dto.getEmail());
		assertNotNull(dto.getFullName());
		assertNotNull(dto.getTelephone());
		assertNotNull(dto.getPwd());
		assertNotNull(dto.getDateRectrutement());
		assertSame(dto.getEmail(), "email@gmail.com");
		assertSame(dto.getFullName(), "fullname");
		assertSame(dto.getTelephone(), "0123456789");
		assertSame(dto.getPwd(), "azertyuiop");
		assertEquals(dto.getDateRectrutement(), LocalDate.of(2019, 11, 20));
	}

	@Test
	public void ConvertAgentNullToAgentDtoRegister() {
		Agent agent = null;
		AgentDtoRegister dto = AgentConverter.agentToAgentDtoRegister(agent);
		assertNull(dto);
	}
	
	@Test
	public void ConvertValidAgentDtoUpdateToAgent() {
		AgentDtoUpdate dto = new AgentDtoUpdate(1, "email@gmail.com", "fullname", "0123456789", "azertyuiop", LocalDate.of(2019, 11, 20));
		Agent agent = AgentConverter.agentDtoUpdateToAgent(dto);
		assertNotNull(agent);
		assertNotNull(agent.getId());
		assertNotNull(agent.getEmail());
		assertNotNull(agent.getFullName());
		assertNotNull(agent.getTelephone());
		assertNotNull(agent.getPwd());
		assertNotNull(agent.getDateRectrutement());
		assertSame(agent.getId(), 1);
		assertSame(agent.getEmail(), "email@gmail.com");
		assertSame(agent.getFullName(), "fullname");
		assertSame(agent.getTelephone(), "0123456789");
		assertSame(agent.getPwd(), "azertyuiop");
		assertEquals(agent.getDateRectrutement(), LocalDate.of(2019, 11, 20));
	}
	
	@Test
	public void ConvertAgentDtoUpdateNullToAgent() {
		AgentDtoUpdate dto = null;
		Agent agent = AgentConverter.agentDtoUpdateToAgent(dto);
		assertNull(agent);
	}

	@Test
	public void ConvertValidAgentToAgentDtoUpdate() {
		Agent agent = new Agent("email@gmail.com", "fullname", "0123456789", "azertyuiop", LocalDate.of(2019, 11, 20));
		AgentDtoUpdate dto = AgentConverter.agentToAgentDtoUpdate(agent);
		assertNotNull(dto);
		assertNotNull(dto.getEmail());
		assertNotNull(dto.getFullName());
		assertNotNull(dto.getTelephone());
		assertNotNull(dto.getPwd());
		assertNotNull(dto.getDateRectrutement());
		assertSame(dto.getEmail(), "email@gmail.com");
		assertSame(dto.getFullName(), "fullname");
		assertSame(dto.getTelephone(), "0123456789");
		assertSame(dto.getPwd(), "azertyuiop");
		assertEquals(dto.getDateRectrutement(), LocalDate.of(2019, 11, 20));
		
	}
	
	@Test
	public void ConvertAgentNullToAgentDtoUpdate() {
		Agent agent = null;
		AgentDtoUpdate dto = AgentConverter.agentToAgentDtoUpdate(agent);
		assertNull(dto);
	}

	@Test
	public void ConvertValidAgentToAgentDtoConnected() {
		Agent agent = new Agent("email@gmail.com", "fullname", "0123456789", "azertyuiop", LocalDate.of(2019, 11, 20));
		AgentDtoConnected dto = AgentConverter.agentToAgentDtoConnected(agent);
		assertNotNull(dto);
		assertNotNull(dto.getEmail());
		assertNotNull(dto.getFullName());
		assertNotNull(dto.getTelephone());
		assertNotNull(dto.getDateRectrutement());
		assertSame(dto.getEmail(), "email@gmail.com");
		assertSame(dto.getFullName(), "fullname");
		assertSame(dto.getTelephone(), "0123456789");
		assertEquals(dto.getDateRectrutement(), LocalDate.of(2019, 11, 20));
	}
	
	@Test
	public void ConvertAgentNullToAgentDtoConnected() {
		Agent agent = null;
		AgentDtoConnected dto = AgentConverter.agentToAgentDtoConnected(agent);
		assertNull(dto);
	}
}
