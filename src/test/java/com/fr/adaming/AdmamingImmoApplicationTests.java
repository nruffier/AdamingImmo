package com.fr.adaming;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AdmamingImmoApplicationTests {
	
	@Autowired
	public MockMvc mvc;
	
	public ObjectMapper mapper = new ObjectMapper();

	@Test
	void contextLoads() {
	}

}