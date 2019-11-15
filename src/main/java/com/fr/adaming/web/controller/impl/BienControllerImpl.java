package com.fr.adaming.web.controller.impl;

import java.util.List;

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

	@Override
	public String create(BienDtoCreate bienDtoCreate) {
		// TODO Auto-generated method stub
		if (service.create(BienConverter.convertBienDtoCreateToBien(bienDtoCreate)) == null) {
			return "Fail SAVE";
		} else {
			return "Success SAVE";
		}
	}

	@Override
	public String update(BienDto biendto) {
		// TODO Auto-generated method stub
		if (service.update(BienConverter.convertBienDtoToBien(biendto)) != null) {
			return "SUCCESS UPDATE";
		} else {
			return "Fail UPDATE";
		}
	}

	@Override
	public void sellBien(Long id) {
		service.sellBien(id);
		// TODO Auto-generated method stub

	}

	@Override
	public Bien getById(Long id) {
		// TODO Auto-generated method stub
		return service.getById(id);
	}

	@Override
	public List<Bien> getAll() {
		// TODO Auto-generated method stub
		return service.getAll();
	}

	public String delete(Long id) {
		// TODO Auto-generated method stub
		if (service.delete(id)) {
			return "SUCCESS DELETE";
		} else {
			return "FAIL DELETE";
		}
	}

}
