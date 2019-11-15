package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.impl.BienServiceImpl;

/**
 * @author Nicolas RUFFIER - Guillaume BRIAS - BILEL ABBAS
 *
 */
@SpringBootTest
public class BienServiceTest {

	@Autowired
	private BienServiceImpl service;

	@Test
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidBien_shouldReturnBienWithIdNotNull() {
		Bien c = new Bien(100000d, false);
		Bien returnedBien = service.create(c);

		assertNotNull(returnedBien);
		assertNotNull(returnedBien.getId());
		assertNotNull(returnedBien.getPrix());
		assertEquals(returnedBien.getPrix(), 100000d);
		assertSame(returnedBien.isVendu(), false);
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createBienWithExistingBien_shouldReturnNull() {
		Bien c = new Bien(1L, 100000d, false);

		Bien returnedBien = service.create(c);

		assertNull(returnedBien);
	}

	@Test
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void createBienWithPrixNull_shouldThrowDataIntegrityViolationException() {
		Bien c = new Bien(1L, 100000d, false);

		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			c.setPrix(null);
			service.create(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidBien_shouldReturnBienWithIdNotNull() {
		Bien c = new Bien(1L, 100001d, false);
		Bien returnedBien = service.update(c);

		assertNotNull(returnedBien);
		assertNotNull(returnedBien.getId());
		assertNotNull(returnedBien.getPrix());
		assertEquals(returnedBien.getPrix(), 100001d);
		assertSame(returnedBien.isVendu(), false);
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateInvalidBien_shouldReturnBienNull() {
		Bien c = new Bien(2L, 100001d, false);
		Bien returnedBien = service.update(c);

		assertNull(returnedBien);
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateBienWithPrixNull_shouldReturnFBUIPFYVIP() {
		Bien c = new Bien(1L, 100001d, false);
		c.setPrix(null);
		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			service.update(c);
		});
		assertEquals(
				"could not execute statement; SQL [n/a]; constraint [null]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement",
				exception.getMessage());
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteBienWithValidId_shouldReturnTrue() {
		boolean returnedresult = service.delete(1L);

		assertTrue(returnedresult);
	}

	@Test
	public void deleteNotExistingBien_shouldReturnFalse() {
		boolean returnedresult = service.delete(846354563L);

		assertFalse(returnedresult);
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getByIdWithValidId_shouldReturnBienNotNull() {
		Bien returnedBien = service.getById(1L);

		assertNotNull(returnedBien);
		assertNotNull(returnedBien.getId());
		assertNotNull(returnedBien.getPrix());
		assertEquals(returnedBien.getPrix(), 100000d);
		assertSame(returnedBien.isVendu(), false);
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getByIdNotExistingBien_shouldReturnNull() {
		Bien returnedBien = service.getById(1546546461L);

		assertNull(returnedBien);
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void sellBienIfExist_shouldReturnTrue() {
		assertTrue(service.sellBien(1L));
	}

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void sellBienIfAlredySold_shouldReturnTrue() {
		assertFalse(service.sellBien(1L));
	}

	@Test
	public void sellBienIfNotExist_shouldReturnTrue() {

		assertThrows(NullPointerException.class, () -> {
			service.sellBien(1L);
		});

	}

	@Test
	@Sql(statements = { "truncate table client",
			"insert into client (id, email, full_name, telephone, type) values(1,'email@gmail.com','fullName1','0123456789', 1)",
			"truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = { "truncate table bien",
			"truncate table client" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void addValidClientToValidBien_shouldReturnTrue() {
		assertTrue(service.addClient(1l, 1));
	}
}
