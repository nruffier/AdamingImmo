package com.fr.adaming.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.controller.BienController;
import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.BienService;

@RestController
public class BienControllerImpl implements BienController {

@Autowired
private BienService service;
		
		
		public Bien create(Bien bien) {
			return bien;
			
		}

		
		
		public Bien update(Bien bien) {
			return bien;
			
		}
		
		public boolean delete(Bien bien) {
			return false;
			
		}

		public Bien findById(Long id) {
			return null;
			
		}

		public List<Bien> getAll(Bien bien){
			return null;
			
		}

		public void sellBien(Long id) {
			
		}
		
		

	}
