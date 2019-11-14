package com.fr.adaming.web.dto.converter;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;



public class BienConverter {

	public static Bien convert(BienDto dto) {
	Bien bien = new Bien();
	bien.setId(dto.getId());
	bien.setPrix(dto.getPrix());
	bien.setVendu(dto.isVendu());
	return bien;
}	
	
	
	public static BienDto convert(Bien bien) {
	BienDto dto = new BienDto();
	dto.setId(bien.getId());
	dto.setPrix(bien.getPrix());
	dto.setVendu(bien.isVendu());
	return dto;
}		
	

	

	
	
}
