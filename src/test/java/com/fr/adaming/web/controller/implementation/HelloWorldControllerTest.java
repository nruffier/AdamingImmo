package com.fr.adaming.web.controller.implementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fr.adaming.AdmamingImmoApplicationTests;
import com.fr.adaming.enumeration.Type;
import com.fr.adaming.web.dto.ClientDto;

public class HelloWorldControllerTest extends AdmamingImmoApplicationTests {
	//Ca c'est juste un exemple du mapper pour convertir enre Json et objet
	@Test
	public void testConvertJsonToJavaObject() throws JsonMappingException, JsonProcessingException {
		String json = "{ \"email\" : \"emafijfdlfq@emdail.dfr\", \"fullName\" : \"fullnameegzrfsBUIbp\", \"telephone\" : \"012345\", \"type\" : \"VENDEUR\"} ";
		ClientDto dto = mapper.readValue(json, ClientDto.class);
		assertNotNull(dto);
		assertEquals("emafijfdlfq@emdail.dfr", dto.getEmail());
		assertEquals("fullnameegzrfsBUIbp", dto.getFullName());
	}
	
	@Test
	public void testConvertJavaObjectToJson() throws JsonProcessingException {
		ClientDto dto = new ClientDto("email", "fullname", "0123456789", Type.VENDEUR);
		
		String json = mapper.writeValueAsString(dto);
		System.out.println("DEBUG Json : " + json);
	}
	
	
	//Ca c'est les tests de la couche controller
	@Test
	public void sayHello_shouldReturnHelloWolrd() throws Exception {
		String result = mvc.perform(get("/api/hello")
			.contentType(MediaType.APPLICATION_JSON))
	    	.andExpect(status().isOk())
	    	.andReturn().getResponse().getContentAsString();
			System.out.println("DEBUG RESULT OF HTTP METHOD : " + result);
			
			assertEquals("HelloWorld From Spring web", result);
	}
	
	@Test
	public void createValidClient_ShouldReturnStatus200AndClientDtoNotNull() throws UnsupportedEncodingException, Exception {
		String result = mvc.perform(post("/api/hello")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{ \"email\" : \"emafijfdlfq@emdail.dfr\", \"fullName\" : \"fullnameegzrfsBUIbp\", \"telephone\" : \"012345\", \"type\" : \"VENDEUR\"} "))
		    	.andExpect(status().isOk())
		    	.andReturn().getResponse().getContentAsString();
		
		System.out.println("DEBUG CREATE CLIENT RESULT : " + result);
	}
	
	
	//Pareil mais avec mapper :
	@Test
	public void MapperCreateValidClient_ShouldReturnStatus200AndClientDtoNotNull() throws UnsupportedEncodingException, Exception {
		//preparer les inputs
		ClientDto dto = new ClientDto("email@email.fr", "fullname", "0123456789", Type.VENDEUR);
		
		
		String result = mvc.perform(post("/api/hello")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(dto)))
		    	.andExpect(status().isOk())
		    	.andReturn().getResponse().getContentAsString();
		
		ClientDto dtoresult = mapper.readValue(result, ClientDto.class);
		
		assertNotNull(dtoresult);
		assertEquals("email@email.fr", dto.getEmail());
		assertEquals("fullname", dto.getFullName());
		assertEquals("0123456789", dto.getTelephone());
		assertEquals(Type.VENDEUR, dto.getType());
				
	}
	
	
}
