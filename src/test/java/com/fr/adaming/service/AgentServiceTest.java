package com.fr.adaming.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.impl.AgentServiceImpl;

/**
 * @author Nicolas RUFFIER
 *
 */
@SpringBootTest
public class AgentServiceTest {

	@Autowired
	private AgentServiceImpl service;
	
	@Test
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidAgent_shouldReturnAgentWithIdNotNull() {
		Agent c = new Agent("email@gmail.com", "fullName1", "0123456789", "azertyuiop");

		Agent returnedAgent = service.create(c);
		assertNotNull(returnedAgent);
		assertNotNull(returnedAgent.getId());
		assertNotNull(returnedAgent.getEmail());
		assertNotNull(returnedAgent.getFullName());
		assertNotNull(returnedAgent.getTelephone());
		assertNotNull(returnedAgent.getPwd());
		assertSame(returnedAgent.getEmail(), "email@gmail.com");
		assertSame(returnedAgent.getFullName(), "fullName1");
		assertSame(returnedAgent.getTelephone(), "0123456789");
		assertSame(returnedAgent.getPwd(), "azertyuiop");
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithExistingEmail_shouldReturnNull() {
		Agent c = new Agent("email@gmail.com", "fullName1", "0123456789", "azertyuiop");

		Agent returnedAgent = service.create(c);

		assertNull(returnedAgent);
	}

	@Test
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createAgentWithEmailNull_shouldThrowDataIntegrityViolationException() {
		Agent c = new Agent("email@gmail.com", "fullName1", "0123456789", "azertyuiop");
		c.setEmail(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.create(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createAgentWithFullNameNull_shouldThrowDataIntegrityViolationException() {
		Agent c = new Agent("email@gmail.com", "fullName1", "0123456789", "azertyuiop");
		c.setFullName(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.create(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createAgentWithFullPwd_shouldThrowDataIntegrityViolationException() {
		Agent c = new Agent("email@gmail.com", "fullName1", "0123456789", "azertyuiop");
		c.setPwd(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.create(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidAgent_shouldReturnAgentWithIdNotNull() {
		Agent c = new Agent(1, "email@gmail.com", "NewfullName", "9876543210", "azertyuiop",
				LocalDate.of(0001, 01, 01));
		Agent returnedAgent = service.update(c);

		assertNotNull(returnedAgent);
		assertNotNull(returnedAgent.getId());
		assertNotNull(returnedAgent.getEmail());
		assertNotNull(returnedAgent.getFullName());
		assertNotNull(returnedAgent.getTelephone());
		assertNotNull(returnedAgent.getPwd());
		assertSame(returnedAgent.getEmail(), "email@gmail.com");
		assertSame(returnedAgent.getFullName(), "NewfullName");
		assertSame(returnedAgent.getTelephone(), "9876543210");
		assertSame(returnedAgent.getPwd(), "azertyuiop");
	}

	@Test
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void updateInvalidAgent_shouldReturnNull() {
		Agent c = new Agent(456418, "email@gmail.com", "NewfullName", "9876543210", "azertyuiop",
				LocalDate.of(0001, 01, 01));
		Agent returnedAgent = service.update(c);

		assertNull(returnedAgent);
	}

	@Test
	public void updateAgentNull_shouldReturnNull() {
		Agent c = new Agent();

		Agent returnedAgent = service.update(c);

		assertNull(returnedAgent);
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateAgentwithEmailNull_shouldReturnNull() {
		Agent c = new Agent(1, "email@gmail.com", "NewfullName", "9876543210", "azertyuiop",
				LocalDate.of(0001, 01, 01));
		c.setEmail(null);

		Agent returnedAgent = service.update(c);

		assertNull(returnedAgent);
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateAgentwithFullNameNull_shouldThrowDataIntegrityViolationException() {
		Agent c = new Agent(1, "email@gmail.com", "NewfullName", "9876543210", "azertyuiop",
				LocalDate.of(0001, 01, 01));
		c.setFullName(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.update(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateAgentwithPwdNull_shouldThrowDataIntegrityViolationException() {
		Agent c = new Agent(1, "email@gmail.com", "NewfullName", "9876543210", "azertyuiop",
				LocalDate.of(0001, 01, 01));
		c.setPwd(null);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.update(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteWithValidId_shouldReturnTrue() {
		boolean returnedresult = service.delete(1);

		assertTrue(returnedresult);
	}

	@Test
	public void deleteNotExistingAgent_shouldReturnFalse() {
		boolean returnedresult = service.delete(846354563);

		assertFalse(returnedresult);
	}

	@Test
	@Sql(statements = {
			"DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getByIdWithValidId_shouldReturnAgentNotNull() {
		Agent returnedAgent = service.getById(1);
		assertNotNull(returnedAgent);
		assertSame(returnedAgent.getId(), 1);
		assertEquals(returnedAgent.getEmail(), "email@gmail.com");
		assertEquals(returnedAgent.getFullName(), "fullName1");
		assertEquals(returnedAgent.getTelephone(), "0123456789");
		assertEquals(returnedAgent.getPwd(), "azertyuiop");
	}

	@Test
	public void getByIdNotExistingAgent_shouldReturnNull() {
		Agent returnedAgent = service.getById(846354563);

		assertNull(returnedAgent);
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void findByEmailWithValidEmail_shouldReturnAgentNotNull() {
		Agent returnedAgent = service.getByEmail("email@gmail.com");
		assertNotNull(returnedAgent);
		assertSame(returnedAgent.getId(), 1);
		assertEquals(returnedAgent.getEmail(), "email@gmail.com");
		assertEquals(returnedAgent.getFullName(), "fullName1");
		assertEquals(returnedAgent.getTelephone(), "0123456789");
		assertEquals(returnedAgent.getPwd(), "azertyuiop");
	}

	@Test
	public void findByEmailWithInvalidEmail_shouldReturnNull() {
		Agent returnedAgent = service.getByEmail("yfjvhqefsdx");

		assertNull(returnedAgent);
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginWithCorrectEmailAndPwd_shouldReturnAgentNotNull() {
		Agent returnedAgent = service.login("email@gmail.com", "azertyuiop");
		assertNotNull(returnedAgent);
		assertSame(returnedAgent.getId(), 1);
		assertEquals(returnedAgent.getEmail(), "email@gmail.com");
		assertEquals(returnedAgent.getFullName(), "fullName1");
		assertEquals(returnedAgent.getTelephone(), "0123456789");
		assertEquals(returnedAgent.getPwd(), "azertyuiop");
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginWithCorrectEmailAndFalsePwd_shouldReturnNull() {
		Agent returnedAgent = service.login("email@gmail.com", "ezbfjkalfbizea");

		assertNull(returnedAgent);
	}

	@Test
	@Sql(statements = { "DELETE FROM AGENT",
			"insert into agent (id, email, full_name, telephone, pwd) values(1,'email@gmail.com','fullName1','0123456789', 'azertyuiop')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM AGENT", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void loginWithFalseEmailAndPwd_shouldReturnNull() {
		Agent returnedAgent = service.login("fbzjeipq", "ezbfjkalfbizea");

		assertNull(returnedAgent);
	}

}
