package com.fr.adaming.web.controller.implementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fr.adaming.AdmamingImmoApplicationTests;
import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentDtoConnected;
import com.fr.adaming.web.dto.AgentDtoLogin;
import com.fr.adaming.web.dto.AgentDtoRegister;
import com.fr.adaming.web.dto.AgentDtoUpdate;

/**
 * @author Nicolas RUFFIER
 *
 */
public class AgentControllerTest extends AdmamingImmoApplicationTests {

	@Test
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidAgent_ShouldReturnStatus200AndAgentDtoConnectedNotNull()
			throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "fullname", "0123456789", "azertyuiop");
		String result = mvc
				.perform(post("/api/agent/create").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		AgentDtoRegister dto = mapper.readValue(result, AgentDtoRegister.class);
		assertNotNull(dto);
		assertEquals("email@gmail.com", dto.getEmail());
		assertEquals("azertyuiop", dto.getPwd());
		assertEquals("fullname", dto.getFullName());
		assertEquals("0123456789", dto.getTelephone());
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithEmailAlreadyExist_ShouldReturnStatus418()
			throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "fullname", "0123456789", "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithEmailInvalid_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("emailgmail", "fullname", "0123456789", "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithTelInvalid_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "fullname", "01234", "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithPwdInvalid_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "fullname", "0123456789", "azerty");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithEmailNull_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(null, "fullname", "0123456789", "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithFullnameNull_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", null, "0123456789", "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithTelNull_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "fullname", null, "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithPwdNull_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "fullname", "0123456789", null);
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}
	
	@Test
	public void createAgentWithEmailBlank_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("", "fullname", "0123456789", "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithFullnameBlank_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "", "0123456789", "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithTelBlank_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "fullname", "", "azertyuiop");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	public void createAgentWithPwdBlank_ShouldReturnStatus418() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent("email@gmail.com", "fullname", "0123456789", "");
		mvc.perform(
				post("/api/agent/create").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().is(418)).andReturn().getResponse().getContentAsString();
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullname','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidAgent_ShouldReturnStatus200AndAgentDtoConnectedNotNull()
			throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, "email@gmail.com", "fullname2", "9876543210", "AZERTYUIOP");
		String result = mvc
				.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		AgentDtoUpdate dto = mapper.readValue(result, AgentDtoUpdate.class);
		assertNotNull(dto);
		assertEquals(1, dto.getId());
		assertEquals("email@gmail.com", dto.getEmail());
		assertEquals("AZERTYUIOP", dto.getPwd());
		assertEquals("fullname2", dto.getFullName());
		assertEquals("9876543210", dto.getTelephone());
	}

	@Test
	public void updateAgentDontExist_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, "email@gmail.com", "fullname", "0123456789", "azertyuiop");
		mvc.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	public void updateAgentWithEmailInvalid_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, "email1@gmail.com", "fullname", "0123456789", "azertyuiop");
		mvc.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	public void updateAgentWithTelInvalid_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, "email@gmail.com", "fullname", "0123459", "azertyuiop");
		mvc.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	public void updateAgentWithPwdinvalid_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, "email@gmail.com", "fullname", "0123456789", "azeriop");
		mvc.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	public void updateAgentWithEmailNull_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, null, "fullname", "0123456789", "azertyuiop");
		mvc.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	public void updateAgentWithFullnameNull_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, "email@gmail.com", null, "0123456789", "azertyuiop");
		mvc.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	public void updateAgentWithTelNull_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, "email@gmail.com", "fullname", null, "azertyuiop");
		mvc.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	public void updateAgentWithPwdNull_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		Agent c = new Agent(1, "email@gmail.com", "fullname", "0123456789", null);
		mvc.perform(post("/api/agent/update").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteValidAgent_ShouldReturnStatut200AndTrue() throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(delete("/api/agent/1/delete")).andDo(print()).andExpect(status().isOk()).andReturn()
				.getResponse().getContentAsString();
		assertEquals("true", result);
	}

	@Test
	public void deleteNotExistingAgent_ShouldReturnStatut200AndFalse() throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(delete("/api/agent/1/delete")).andDo(print()).andExpect(status().isOk()).andReturn()
				.getResponse().getContentAsString();
		assertEquals("false", result);
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullname','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getByIdWithValidId_shouldReturnStatut200AndAgentNotNull()
			throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(get("/api/agent/1/get-id")).andDo(print()).andExpect(status().isOk()).andReturn()
				.getResponse().getContentAsString();

		Agent agent = mapper.readValue(result, Agent.class);
		assertNotNull(agent);
		assertEquals(1, agent.getId());
		assertEquals("email@gmail.com", agent.getEmail());
		assertEquals("azertyuiop", agent.getPwd());
		assertEquals("fullname", agent.getFullName());
		assertEquals("0123456789", agent.getTelephone());
	}

	@Test
	public void getByIdWithInvalidId_shouldReturnStatut200AndAgentNotNull()
			throws UnsupportedEncodingException, Exception {
		mvc.perform(get("/api/agent/1/get-id")).andDo(print()).andExpect(status().is(400)).andReturn().getResponse()
				.getContentAsString();
	}

	@Test
	public void getAllWithDBEmpty_shouldReturnEmptyList() throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(get("/api/agent/get-all")).andDo(print()).andExpect(status().isOk()).andReturn()
				.getResponse().getContentAsString();

		List<Agent> list = mapper.readValue(result, new TypeReference<List<Agent>>() {
		});
//		Agent[] list = mapper.readValue(result, Agent[].class);
		assertTrue(list.isEmpty());
		System.out.println("****" + result + "****");
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullname','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginValid_shouldReturnStatut200AndAgentNotNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		AgentDtoLogin c = new AgentDtoLogin("email@gmail.com", "azertyuiop");
		String result = mvc
				.perform(post("/api/agent/login").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(c)))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		AgentDtoConnected agent = mapper.readValue(result, AgentDtoConnected.class);
		assertNotNull(agent);
		assertEquals(1, agent.getId());
		assertEquals("email@gmail.com", agent.getEmail());
		assertEquals("fullname", agent.getFullName());
		assertEquals("0123456789", agent.getTelephone());
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullname','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginWithValidEmailAndInvalidPwd_shouldReturnStatut400()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		AgentDtoLogin c = new AgentDtoLogin("email@gmail.com", "");
		mvc.perform(post("/api/agent/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullname','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginWithInvalidEmailAndValidPwd_shouldReturnStatut400()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		AgentDtoLogin c = new AgentDtoLogin("", "azertyuiop");
		mvc.perform(post("/api/agent/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullname','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginWithInvalidEmailAndInvalidPwd_shouldReturnStatut400()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		AgentDtoLogin c = new AgentDtoLogin("", "");
		mvc.perform(post("/api/agent/login").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(c))).andDo(print()).andExpect(status().is(400)).andReturn()
				.getResponse().getContentAsString();
	}

}
