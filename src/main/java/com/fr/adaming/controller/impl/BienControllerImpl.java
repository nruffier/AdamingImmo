package com.fr.adaming.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.BienController;
import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.BienService;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.converter.BienConverter;

@RestController
public class BienControllerImpl implements BienController {

	@Autowired 
	private BienService service;

	@Override
	public String create(BienDto biendto) {
		// TODO Auto-generated method stub
		if (service.create(BienConverter.convert(biendto)) == null) {
			return "Fail SAVE";
		} else {
			return "Success SAVE";
		}
	}

	@Override
	public String update(BienDto biendto) {
		// TODO Auto-generated method stub
		if (service.create(BienConverter.convert(biendto)) == null) {
			return "Fail UPDATE";
		} else {
			return "Success UPDATE";
		}
	}

	@Override
	public void sellBien(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Bien findById(Long id) {
		// TODO Auto-generated method stub
		return service.findById(id);
	}

	@Override
	public List<Bien> getAll() {
		// TODO Auto-generated method stub
		return service.getAll();
	}

	@Override
	public String delete(BienDto biendto) {
		// TODO Auto-generated method stub
		if (service.delete(BienConverter.convert(biendto))) {
			return "SUCCESS DELETE";
		} else {
			return "FAIL DELETE";
		}
	}

}
