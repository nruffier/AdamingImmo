package com.fr.adaming.web.dto.converter;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.BienDtoCreate;

/**
 * @author bilel
 *
 */
public class BienConverter {

	public static Bien convertBienDtoToBien(BienDto dto) {
		
		if(dto == null) {
			return null;
		}
		
		Bien bien = new Bien();
		bien.setId(dto.getId());
		bien.setPrix(dto.getPrix());
		bien.setVendu(dto.isVendu());
		return bien;
	}

	public static BienDto convertBienToBienDto(Bien bien) {
		if(bien == null) {
			return null;
		}
		BienDto dto = new BienDto();
		dto.setId(bien.getId());
		dto.setPrix(bien.getPrix());
		dto.setVendu(bien.isVendu());
		return dto;

	}

	public static Bien convertBienDtoCreateToBien(BienDtoCreate dto) {
		
		if(dto == null) {
			return null;
		}
		
		
		Bien bien = new Bien();
		bien.setPrix(dto.getPrix());
		bien.setVendu(dto.isVendu());
		return bien;
	}

	public static BienDtoCreate convertBienToBienDtoCreate(Bien bien) {
		
		if(bien == null) {
			return null;
		}
		
		
		BienDtoCreate dto = new BienDtoCreate();
		dto.setPrix(bien.getPrix());
		dto.setVendu(bien.isVendu());
		return dto;
	}
}
