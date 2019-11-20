package com.fr.adaming.web.controller.implementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.AdmamingImmoApplicationTests;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.BienDtoCreate;

public class BienControllerTest extends AdmamingImmoApplicationTests {

	// CREATION 
	
	@Test
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void CreateValidBien_ShouldReturnStatus200AndBienDtoCreateNotNull()
			throws UnsupportedEncodingException, Exception {
		// preparer les inputs
		BienDtoCreate dto = new BienDtoCreate(12d, false);

		String result = mvc
				.perform(post("/api/bien/create").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		BienDtoCreate dtoresult = mapper.readValue(result, BienDtoCreate.class);

		assertNotNull(dtoresult);
		assertEquals(12d, dto.getPrix());
		assertEquals(false, dto.isVendu());

	}

	@Test
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void CreateInValidBien_WithPrixNull_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		//preparer les inputs
		BienDtoCreate dto = new BienDtoCreate(0d, false);
		
		
		mvc.perform(post("/api/bien/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
		    	.andExpect(status().is(400))
		    	.andReturn().getResponse().getContentAsString();
					
	}
	
	@Test
	@Sql(statements = "truncate table bien", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void CreateInValidBien_WithPrixNegative_ShouldReturnStatus400() throws UnsupportedEncodingException, Exception {
		//preparer les inputs
		BienDtoCreate dto = new BienDtoCreate(-4d, false);
		
		
		mvc.perform(post("/api/bien/create")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
		    	.andExpect(status().is(400))
		    	.andReturn().getResponse().getContentAsString();
					
	}
	
	

	// UPDATE
	
	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void UpdateValidBien_ShouldReturnStatus200AndBienDtoUpdateNotNull()
			throws UnsupportedEncodingException, Exception {
		// preparer les inputs
		BienDto dto = new BienDto(1l, 100000d, false);

		dto.setPrix(45d);

		String result = mvc
				.perform(put("/api/bien/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andDo(print()).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		BienDto dtoresult = mapper.readValue(result, BienDto.class);

		assertNotNull(dtoresult);
		assertEquals(45d, dto.getPrix());
		assertEquals(false, dto.isVendu());

	}
	
	@Test
	@Sql(statements = { "truncate table bien"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateNotExistingBien_ShouldReturnStatus400()
			throws UnsupportedEncodingException, Exception {
		// preparer les inputs
		BienDto dto = new BienDto(1l, 100000d, false);

		dto.setPrix(45d);

		
		String bodyAsJson = mvc.perform(put("/api/bien/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(dto)))
				.andDo(print()).andExpect(status().is(200)).andReturn().getResponse().getContentAsString();

		//Faut recevoir une HttpResponse avec un objet BienDto dans le corp (Body) égale à null
	
		assertEquals("", bodyAsJson);	//reponse sera null
		

	}
	
	
	
	// GET ALL

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAll_shouldReturnAllBiens() throws Exception {
		String result = mvc.perform(get("/api/bien/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("DEBUG GET ALL : " + result);

	}
	
	
	@Test
	@Sql(statements = { "truncate table bien"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void getAll_shouldReturnListBienEmpty() throws Exception {
		String result = mvc.perform(get("/api/bien/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		System.out.println("DEBUG GET ALL : " + result);

	}
	
	
	// GET ID

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void GetValidBien_ShouldReturnStatus200AndBienDtoUpdateNotNull() throws Exception {

		String result = mvc.perform(get("/api/bien/1/get-id/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		System.out.println("debug test GET ID" + result);

	}
	
	
	@Test
	@Sql(statements = { "truncate table bien"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void GetNotExistBien() throws Exception {

		String result = mvc.perform(get("/api/bien/1/get-id/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		System.out.println("debug test GET ID" + result);

	}
	
	
//get id with id not exist

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void DeleteValidBien_ShouldReturnStatus200AndBienDtoUpdateNotNull() throws Exception {

		String result = mvc.perform(delete("/api/bien/1/delete/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("SUCCESS DELETE", result);

	}
	
	//delete with id not exist

	@Test
	@Sql(statements = { "truncate table bien",
			"insert into bien (id, prix, vendu) values(1, 100000, 0)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from bien", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void SellBienValidBien_ShouldReturnStatus200AndBienDtoUpdateNotNull() throws Exception {

		String result = mvc.perform(put("/api/bien/1/sell-id/").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		System.out.println(result);
		assertEquals("Maison vendue", result);

	}
	
	//sell already sell

}
