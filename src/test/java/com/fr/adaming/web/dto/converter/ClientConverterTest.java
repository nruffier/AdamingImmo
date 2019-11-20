package com.fr.adaming.web.dto.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import com.fr.adaming.entity.Client;
import com.fr.adaming.enumeration.Type;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.ClientDtoUpdate;

/**
 * @author Guillaume BRIAS
 *
 */
public class ClientConverterTest {
	
	@Test
	public void convertNotNullClientToClientDto_ShouldReturnClientDtoNotNull() {
		
		Client client = new Client("email@gmail.com", "fullname", "0123456789", Type.ACHETEUR);
		
		ClientDto returnedClientDto = ClientConverter.convertClientToClientDto(client);
		
		assertNotNull(returnedClientDto);
		assertEquals(client.getEmail(), returnedClientDto.getEmail());
		assertEquals(client.getFullName(), returnedClientDto.getFullName());
		assertEquals(client.getTelephone(), returnedClientDto.getTelephone());
		assertEquals(client.getType(), returnedClientDto.getType());
		
	}
	
	@Test
	public void convertNullClientToClientDto_ShouldReturnNull() {
		
		assertNull(ClientConverter.convertClientToClientDto(null));
		
	}
	
	@Test
	public void convertNotNullClientDtoToClient_ShouldReturnClientNotNull() {
		
		ClientDto dto = new ClientDto("email@gmail.com", "fullname", "0123456789", Type.ACHETEUR);
		
		Client returnedClient = ClientConverter.convertClientDtoToClient(dto);
		
		assertNotNull(returnedClient);
		assertEquals(dto.getEmail(), returnedClient.getEmail());
		assertEquals(dto.getFullName(), returnedClient.getFullName());
		assertEquals(dto.getTelephone(), returnedClient.getTelephone());
		assertEquals(dto.getType(), returnedClient.getType());
		
	}
	
	@Test
	public void convertNullClientDtoToClient_ShouldReturnNull() {
		
		assertNull(ClientConverter.convertClientDtoToClient(null));
		
	}
	
	@Test
	public void convertNotNullClientToClientDtoUpdate_ShouldReturnClientDtoUpdateNotNull() {
		
		Client client = new Client(1,"email@gmail.com", "fullname", "0123456789", Type.ACHETEUR);
		
		ClientDtoUpdate returnedClientDtoUpdate = ClientConverter.convertClientToClientDtoUpdate(client);
		
		assertNotNull(returnedClientDtoUpdate);
		assertEquals(client.getId(), returnedClientDtoUpdate.getId());
		assertEquals(client.getEmail(), returnedClientDtoUpdate.getEmail());
		assertEquals(client.getFullName(), returnedClientDtoUpdate.getFullName());
		assertEquals(client.getTelephone(), returnedClientDtoUpdate.getTelephone());
		assertEquals(client.getType(), returnedClientDtoUpdate.getType());
		
	}
	
	@Test
	public void convertNullClientToClientDtoUpdate_ShouldReturnNull() {
		
		assertNull(ClientConverter.convertClientToClientDtoUpdate(null));
		
	}
	
	@Test
	public void convertNotNullClientDtoUpdateToClient_ShouldReturnClientNotNull() {
		
		ClientDtoUpdate dto = new ClientDtoUpdate(1, "email@gmail.com", "fullname", "0123456789", Type.ACHETEUR);
		
		Client returnedClient = ClientConverter.convertClientDtoUpdateToClient(dto);
		
		assertNotNull(returnedClient);
		assertEquals(dto.getId(), returnedClient.getId());
		assertEquals(dto.getEmail(), returnedClient.getEmail());
		assertEquals(dto.getFullName(), returnedClient.getFullName());
		assertEquals(dto.getTelephone(), returnedClient.getTelephone());
		assertEquals(dto.getType(), returnedClient.getType());
		
	}
	
	@Test
	public void convertNullClientDtoUpdateToClient_ShouldReturnNull() {
		
		assertNull(ClientConverter.convertClientDtoUpdateToClient(null));
		
	}


}
