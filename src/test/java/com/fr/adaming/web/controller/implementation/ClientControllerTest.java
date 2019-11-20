package com.fr.adaming.web.controller.implementation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fr.adaming.AdmamingImmoApplicationTests;
import com.fr.adaming.entity.Client;
import com.fr.adaming.enumeration.Type;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.ClientDtoUpdate;

/**
 * @author Brias Guillaume
 *
 */
public class ClientControllerTest extends AdmamingImmoApplicationTests {

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidClient_ShouldReturnStatus200AndClientNotNull()
			throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("email@email.fr", "fullname", "0123456789", Type.VENDEUR);

		String result = mvc
				.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Client returnedClient = mapper.readValue(result, Client.class);

		assertNotNull(returnedClient);
		assertEquals(dto.getEmail(), returnedClient.getEmail());
		assertEquals(dto.getFullName(), returnedClient.getFullName());
		assertEquals(dto.getTelephone(), returnedClient.getTelephone());
		assertEquals(dto.getType(), returnedClient.getType());

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createClientWithExistingEmail_ShouldReturnStatus200AndNull()
			throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("email@gmail.com", "fullname", "0123456789", Type.VENDEUR);

		String result = mvc
				.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(result.isEmpty());

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithInvalidEmail_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("eqfsdxc", "fullname", "0123456789", Type.VENDEUR);

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithInvalidSizeTelephoneNumber_ShouldReturnStatus400()
			throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("email@email.fr", "fullname", "012345", Type.VENDEUR);

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithInvalidTelephoneContent_ShouldReturnStatus400()
			throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("email@email.fr", "fullname", "azertyuiop", Type.VENDEUR);

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}
	
	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithNullTelephone_ShouldReturnStatus400()
			throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("email@email.fr", "fullname", null, Type.VENDEUR);

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithBlankFullname_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("email@email.fr", "     ", "azertyuiop", Type.VENDEUR);

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithNullFullname_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("email@email.fr", null, "azertyuiop", Type.VENDEUR);

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithBlankType_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(
				"{\"email\":\"email@gmail.fr\",\"fullName\":\"fullname\",\"telephone\":\"0123456789\",\"type\":\"    \"}"))
				.andExpect(status().is(400));

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithInvalidType_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON).content(
				"{\"email\":\"email@gmail.fr\",\"fullName\":\"fullname\",\"telephone\":\"0123456789\",\"type\":\"INVALIDTYPE\"}"))
				.andExpect(status().is(400));

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithNullType_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDto dto = new ClientDto("email@email.fr", "fullname", "azertyuiop", null);

		mvc.perform(post("/api/client/create").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidClient_ShouldReturnStatus200AndClientNotNull()
			throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", "fullname", "9876543210", Type.ACHETEUR);

		String result = mvc
				.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Client returnedClient = mapper.readValue(result, Client.class);

		assertNotNull(returnedClient);
		assertEquals(dto.getEmail(), returnedClient.getEmail());
		assertEquals(dto.getFullName(), returnedClient.getFullName());
		assertEquals(dto.getTelephone(), returnedClient.getTelephone());
		assertEquals(dto.getType(), returnedClient.getType());

	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateInvalidClient_ShouldReturnStatus200AndClientNull()
			throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", "fullname", "9876543210", Type.ACHETEUR);

		String result = mvc
				.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(result.isEmpty());

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithInvalidEmail_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "qzefsd", "fullname", "9876543210", Type.ACHETEUR);

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithInvalidSizeTelephoneNumber_ShouldReturnStatus400()
			throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", "fullname", "543210", Type.ACHETEUR);

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithInvalidTelephoneContent_ShouldReturnStatus400()
			throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", "fullname", "azertyuiop", Type.ACHETEUR);

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}
	
	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithNullTelephone_ShouldReturnStatus400()
			throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", "fullname", null, Type.ACHETEUR);

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithNullId_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(null, "email@gmail.com", "fullname", "0123456789", Type.ACHETEUR);

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithBlankFullname_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", "    ", "0123456789", Type.ACHETEUR);

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithNullFullname_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", null, "0123456789", Type.ACHETEUR);

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithBlankType_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON).content(
				"{\"id\":\"1\",\"email\":\"email@gmail.fr\",\"fullName\":\"fullname\",\"telephone\":\"0123456789\",\"type\":\"    \"}"))
				.andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithInvalidType_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON).content(
				"{\"id\":\"1\",\"email\":\"email@gmail.fr\",\"fullName\":\"fullname\",\"telephone\":\"0123456789\",\"type\":\"INVALIDTYPE\"}"))
				.andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithNullType_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {

		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", "fullname", "0123456789", null);

		mvc.perform(put("/api/client/update").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto))).andExpect(status().is(400));

	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteWithValidId_shouldReturnStatus200AndSuccessMessage()
			throws UnsupportedEncodingException, Exception {

		String result = mvc.perform(delete("/api/client/1/delete").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("SUCCESS DELETE CLIENT", result);
	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteWithInvalidId_shouldReturnStatus200AndFailMessage()
			throws UnsupportedEncodingException, Exception {

		String result = mvc.perform(delete("/api/client/643551/delete").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("FAIL DELETE CLIENT", result);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email1@gmail.com','fullName1','0123456789', 1)",
			"insert into client (id, email, full_name, telephone, type) values(2,'email2@gmail.com','fullName2','9876543210', 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAllWhenClientsAreRegistered_shouldReturnStatus200AndListOfClientNotNull()
			throws UnsupportedEncodingException, Exception {

		String result = mvc.perform(get("/api/client/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		List<Client> clients = mapper.readValue(result, new TypeReference<ArrayList<Client>>() {
		});

		assertFalse(clients.isEmpty());
		assertEquals(clients.get(0).getId(), 1);
		assertEquals(clients.get(0).getEmail(), "email1@gmail.com");
		assertEquals(clients.get(0).getFullName(), "fullName1");
		assertEquals(clients.get(0).getTelephone(), "0123456789");
		assertEquals(clients.get(0).getType(), Type.VENDEUR);
		assertEquals(clients.get(1).getId(), 2);
		assertEquals(clients.get(1).getEmail(), "email2@gmail.com");
		assertEquals(clients.get(1).getFullName(), "fullName2");
		assertEquals(clients.get(1).getTelephone(), "9876543210");
		assertEquals(clients.get(1).getType(), Type.ACHETEUR);
	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void getAllWhenNoClientsAreRegistered_shouldReturnStatus200AndEmptyList()
			throws UnsupportedEncodingException, Exception {

		String result = mvc.perform(get("/api/client/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		List<Client> clients = mapper.readValue(result, new TypeReference<ArrayList<Client>>() {
		});

		assertTrue(clients.isEmpty());
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void getByIdWithValidId_shouldReturnStatus200AndClientNotNull()
			throws UnsupportedEncodingException, Exception {

		String result = mvc.perform(get("/api/client/1/get-id").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		Client returnedClient = mapper.readValue(result, Client.class);

		assertNotNull(returnedClient);
		assertEquals(1, returnedClient.getId());
		assertEquals("email@gmail.com", returnedClient.getEmail());
		assertEquals("fullName1", returnedClient.getFullName());
		assertEquals("0123456789", returnedClient.getTelephone());
		assertEquals(Type.VENDEUR, returnedClient.getType());
	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void getByIdWithInvalidId_shouldReturnStatus200AndClientNull()
			throws UnsupportedEncodingException, Exception {

		String result = mvc.perform(get("/api/client/5312/get-id").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("", result);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT", "DELETE FROM AGENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = { "DELETE FROM CLIENT", "DELETE FROM AGENT" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addValidAgentToValidClient_shouldReturnStatus200AndSuccessMessage()
			throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(get("/api/client/1/add-agent/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("SUCCESS addAgent", result);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT", "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addValidAgentToInvalidClient_shouldReturnStatus200AndFailMessage()
			throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(get("/api/client/1/add-agent/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("FAIL addAgent", result);
	}
	
	@Test
	@Sql(statements = { "DELETE FROM CLIENT", "DELETE FROM AGENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addInvalidAgentToValidClient_shouldReturnStatus200AndFailMessage()
			throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(get("/api/client/1/add-agent/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("FAIL addAgent", result);
	}

}