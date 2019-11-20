package com.fr.adaming.web.dto.converter;

import org.springframework.stereotype.Component;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientDto;
import com.fr.adaming.web.dto.ClientDtoUpdate;

/**
 * @author Brias Gullaume
 *
 */
@Component
public class ClientConverter {

	public static ClientDto convertClientToClientDto(Client client) {
		if (client != null) {
			ClientDto dto = new ClientDto();
			dto.setEmail(client.getEmail());
			dto.setFullName(client.getFullName());
			dto.setTelephone(client.getTelephone());
			dto.setType(client.getType());
			return dto;
		} else {
			return null;
		}

	}

	public static Client convertClientDtoToClient(ClientDto dto) {
		if (dto != null) {
			Client client = new Client();
			client.setEmail(dto.getEmail());
			client.setFullName(dto.getFullName());
			client.setTelephone(dto.getTelephone());
			client.setType(dto.getType());
			return client;
		} else {
			return null;
		}

	}

	public static ClientDtoUpdate convertClientToClientDtoUpdate(Client client) {
		if (client != null) {
			ClientDtoUpdate dto = new ClientDtoUpdate();
			dto.setEmail(client.getEmail());
			dto.setFullName(client.getFullName());
			dto.setTelephone(client.getTelephone());
			dto.setType(client.getType());
			dto.setId(client.getId());
			return dto;
		} else {
			return null;
		}

	}

	public static Client convertClientDtoUpdateToClient(ClientDtoUpdate dto) {
		if (dto != null) {
			Client client = new Client();
			client.setEmail(dto.getEmail());
			client.setFullName(dto.getFullName());
			client.setTelephone(dto.getTelephone());
			client.setType(dto.getType());
			client.setId(dto.getId());
			return client;
		} else {
			return null;
		}

	}

}
