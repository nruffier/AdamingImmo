package com.fr.adaming.web.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.web.controller.BienController;
import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.BienService;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.BienDtoCreate;
import com.fr.adaming.web.dto.converter.BienConverter;

/**
 * @author bilel
 *
 */
@RestController
public class BienControllerImpl implements BienController {

	@Autowired
	private BienService service;
    
	

//	@Override
//	public String create(BienDtoCreate bienDtoCreate) {
//		if (service.create(BienConverter.convertBienDtoCreateToBien(bienDtoCreate)) == null) {
//			return "Fail SAVE";
//		} else {
//			return "Success SAVE";
//		}
//	}
	
	

//	@Override
//	public String update(BienDto biendto) {
//		if (service.update(BienConverter.convertBienDtoToBien(biendto)) != null) {
//			return "SUCCESS UPDATE";
//		} else {
//			return "Fail UPDATE";
//		}
//	}

//	@Override
//	public void sellBien(Long id) {
//		service.sellBien(id);
//
//	}

	@Override
	public Bien getById(Long id) {
		return service.getById(id);
	}

	@Override
	public List<Bien> getAll() {
		return service.getAll();
	}

	@Override
	public String delete(Long id) {
		if (service.delete(id)) {
			return "SUCCESS DELETE";
		} else {
			return "FAIL DELETE";
		}
	}

	@Override
	public String addClient(Long idBien, Integer idClient) {
		if (service.addClient(idBien, idClient)) {
			return "SUCCESS addClient";
		} else {
			return "FAIL addClient";
		}
	}

	@Override
	public BienDtoCreate create(@Valid BienDtoCreate bienDtoCreate) {

		return BienConverter.convertBienToBienDtoCreate(service.create(BienConverter.convertBienDtoCreateToBien(bienDtoCreate)))  ;
	
}

	@Override
	public BienDto update(@Valid BienDto biendto) {
		return BienConverter.convertBienToBienDto(service.update(BienConverter.convertBienDtoToBien(biendto)))  ;
	}

	@Override
	public String sellBien(Long id) {
		service.sellBien(id);
		return "Maison vendue";
	}
	
	
	
	
}


