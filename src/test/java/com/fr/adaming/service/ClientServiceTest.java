package com.fr.adaming.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Client;
import com.fr.adaming.enumeration.Type;
import com.fr.adaming.service.impl.ClientServiceImpl;

/**
 * @author Guillaume BRIAS
 *
 */
@SpringBootTest
public class ClientServiceTest {

	@Autowired
	private ClientServiceImpl service;

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidClient_shouldReturnClientWithIdNotNull() {
		Client c = new Client("email@gmail.com", "fullName1", "0123456789", Type.ACHETEUR);

		Client returnedClient = service.create(c);

		assertNotNull(returnedClient);
		assertNotNull(returnedClient.getId());
		assertNotNull(returnedClient.getEmail());
		assertNotNull(returnedClient.getFullName());
		assertNotNull(returnedClient.getTelephone());
		assertNotNull(returnedClient.getType());
		assertSame(returnedClient.getEmail(), "email@gmail.com");
		assertSame(returnedClient.getFullName(), "fullName1");
		assertSame(returnedClient.getTelephone(), "0123456789");
		assertSame(returnedClient.getType(), Type.ACHETEUR);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createClientWithExistingEmail_shouldReturnNull() {
		Client c = new Client("email@gmail.com", "fullName2", "9876543210", Type.VENDEUR);

		Client returnedClient = service.create(c);

		assertNull(returnedClient);
	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithEmailNull_shouldThrowDataIntegrityViolationException() {
		Client c = new Client("email@gmail.com", "fullName1", "0123456789", Type.VENDEUR);
		c.setEmail(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.create(c);
		});
//		System.out.println("ICIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII -> " + exception.getMessage());
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithFullNameNull_shouldThrowDataIntegrityViolationException() {
		Client c = new Client("email@gmail.com", "fullName1", "0123456789", Type.VENDEUR);
		c.setFullName(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.create(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithTypeNull_shouldThrowDataIntegrityViolationException() {
		Client c = new Client("email@gmail.com", "fullName1", "0123456789", Type.VENDEUR);
		c.setType(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.create(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createClientWithInvalidType_shouldThrowIllegalArgumentException() {
		Client c = new Client("email@gmail.com", "fullName1", "0123456789", Type.VENDEUR);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			c.setType(Type.valueOf("Mauvais Type"));
			service.create(c);
		});
		assertEquals("No enum constant com.fr.adaming.enumeration.Type.Mauvais Type", exception.getMessage());
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidClient_shouldReturnClientWithIdNotNull() {
		Client c = new Client(1, "email@gmail.com", "fullName2", "9876543210", Type.VENDEUR);

		Client returnedClient = service.update(c);

		assertNotNull(returnedClient);
		assertNotNull(returnedClient.getId());
		assertNotNull(returnedClient.getEmail());
		assertNotNull(returnedClient.getFullName());
		assertNotNull(returnedClient.getTelephone());
		assertNotNull(returnedClient.getType());
		assertSame(returnedClient.getEmail(), "email@gmail.com");
		assertSame(returnedClient.getFullName(), "fullName2");
		assertSame(returnedClient.getTelephone(), "9876543210");
		assertSame(returnedClient.getType(), Type.VENDEUR);
	}

	@Test
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void updateInvalidClient_shouldReturnNull() {
		Client c = new Client(1, "email@gmail.com", "fullName2", "9876543210", Type.VENDEUR);

		Client returnedClient = service.update(c);

		assertNull(returnedClient);
	}

	@Test
	public void updateClientNull_shouldReturnNull() {
		Client c = new Client();

		Client returnedClient = service.update(c);

		assertNull(returnedClient);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientwithEmailNull_shouldReturnNull() {
		Client c = new Client(1, "email@gmail.com", "fullName2", "9876543210", Type.VENDEUR);
		c.setEmail(null);

		Client returnedClient = service.update(c);

		assertNull(returnedClient);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientwithFullNameNull_shouldThrowDataIntegrityViolationException() {
		Client c = new Client(1, "email@gmail.com", "fullName2", "9876543210", Type.VENDEUR);
		c.setFullName(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.update(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientwithTypeNull_shouldThrowDataIntegrityViolationException() {
		Client c = new Client(1, "email@gmail.com", "fullName2", "9876543210", Type.VENDEUR);
		c.setType(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.update(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithInvalidType_shouldThrowIllegalArgumentException() {
		Client c = new Client(1, "email@gmail.com", "fullName1", "0123456789", Type.VENDEUR);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			c.setType(Type.valueOf("Mauvais Type"));
			service.update(c);
		});
		assertEquals("No enum constant com.fr.adaming.enumeration.Type.Mauvais Type", exception.getMessage());
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClientWithExistingEmailButNullEverywhereElse_shouldThrowDataIntegrityViolationException() {
		Client c = new Client("email@gmail.com", "fullName2", "9876543210", Type.VENDEUR);
		c.setFullName(null);
		c.setTelephone(null);
		c.setType(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.update(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteWithValidId_shouldReturnTrue() {
		boolean returnedresult = service.delete(1);

		assertTrue(returnedresult);
	}

	@Test
	public void deleteNotExistingClient_shouldReturnFalse() {
		boolean returnedresult = service.delete(846354563);

		assertFalse(returnedresult);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getByIdWithValidId_shouldReturnClientNotNull() {
		Client returnedClient = service.getById(1);
		System.out.println("DEBUG TEST VALIDE getById() " + returnedClient);
		System.out.println("DEBUG TEST VALIDE getById() " + returnedClient.getEmail());
		assertNotNull(returnedClient);
		assertSame(returnedClient.getId(), 1);
		assertEquals(returnedClient.getEmail(), "email@gmail.com");
		assertEquals(returnedClient.getFullName(), "fullName1");
		assertEquals(returnedClient.getTelephone(), "0123456789");
		assertEquals(returnedClient.getType(), Type.VENDEUR);
	}

	@Test
	public void getByIdNotExistingClient_shouldReturnNull() {
		Client returnedClient = service.getById(846354563);

		assertNull(returnedClient);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void findByEmailWithValidEmail_shouldReturnClientNotNull() {
		Client returnedClient = service.findByEmail("email@gmail.com");
		assertNotNull(returnedClient);
		assertSame(returnedClient.getId(), 1);
		assertEquals(returnedClient.getEmail(), "email@gmail.com");
		assertEquals(returnedClient.getFullName(), "fullName1");
		assertEquals(returnedClient.getTelephone(), "0123456789");
		assertEquals(returnedClient.getType(), Type.VENDEUR);
	}

	@Test
	public void findByEmailWithInvalidEmail_shouldReturnNull() {
		Client returnedClient = service.findByEmail("yfjvhqefsdx");

		assertNull(returnedClient);
	}

	@Test
	@Sql(statements = { "DELETE FROM CLIENT", "DELETE FROM AGENT",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"DELETE FROM CLIENT", "DELETE FROM AGENT"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addValidAgentToValidClient_shouldReturnTrue() {
		assertTrue(service.addAgent(1, 1));
	}

}
