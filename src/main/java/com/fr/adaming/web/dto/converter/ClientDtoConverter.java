package com.fr.adaming.web.dto.converter;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientDto;

public class ClientDtoConverter {
	
	public static ClientDto convertClientToClientDto(Client client) {
		ClientDto dto = new ClientDto();
		dto.setEmail(client.getEmail());
		dto.setFullName(client.getFullName());
		dto.setTelephone(client.getTelephone());
		dto.setType(client.getType());
		return dto;
	}
	
	public static Client convertClientDtoToClient(ClientDto dto) {
		Client client = new Client();
		client.setEmail(dto.getEmail());
		client.setFullName(dto.getFullName());
		client.setTelephone(dto.getTelephone());
		client.setType(dto.getType());
		return client;
	}

}
