package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.repository.BienRepository;
import com.fr.adaming.service.BienService;

/**
 * @author bilel
 *
 */
@Service
public class BienServiceImpl implements BienService {

	@Autowired
	private BienRepository repo;

	/** Methode : Pour créer un nouveau bien
	 *
	 */
	@Override
	public Bien create(Bien bien) {
		// TODO Auto-generated method stub
		if (repo.existsById(bien.getId())) {
			return null;
		} else {
			return repo.save(bien);
		}
	}

	/** Methode : Pour faire une mise à jour du bien
	 *
	 */
	@Override
	public boolean update(Bien bien) {
		// TODO Auto-generated method stub
		if (repo.existsById(bien.getId())) {
			repo.save(bien);
			return true;
		} else {
			return false;
		}
	}


	/** Methode : Pour supprimer un bien
	 *
	 */
	@Override
	public boolean delete(Bien bien) {
		// TODO Auto-generated method stub
		if (repo.existsById(bien.getId())) {
			repo.delete(bien);
			return true;
		} else {
			return false;
		}
	}

	/**  Methode pour afficher un bien selon son id
	 *
	 */
	@Override
	public Bien findById(Long id) {
		// TODO Auto-generated method stub
		
		try {
			return repo.findById(id).get();
			}
		
	    catch (NoSuchElementException e) {
	    	return null;
	    }
	}

	/** Methode : Pour afficher tout les biens 
	 *
	 */
	@Override
	public List<Bien> getAll(Bien bien) {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
