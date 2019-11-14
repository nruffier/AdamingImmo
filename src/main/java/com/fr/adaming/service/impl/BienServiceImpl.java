package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.repository.BienRepository;
import com.fr.adaming.service.BienService;

@Service
public class BienServiceImpl implements BienService {
	
	private BienRepository bienrepo;

	@Override
	public Bien create(Bien bien) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Bien bien) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Bien bien) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bien findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bien> getAll(Bien bien) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
