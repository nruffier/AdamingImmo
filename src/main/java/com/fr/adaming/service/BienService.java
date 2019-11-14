package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Bien;

public interface BienService {
	
	
public Bien create(Bien bien);
public Bien update(Bien bien);
public boolean delete(Bien bien);
public Bien findById(Long id);
public List<Bien> getAll(Bien bien);

	

}
