package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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

	/**
	 * Methode : Pour créer un nouveau bien
	 *
	 * @param
	 * @return Retourne null si l'objet n'existe pas sinon il affiche l'objet bien
	 */
	@Override
	public Bien create(Bien bien) {
		// TODO Auto-generated method stub
		if (repo.exists(Example.of(bien))) {
			return null;
		} else {
			return repo.save(bien);
		}
	}

	/**
	 * Methode : Pour faire une mise à jour du bien
	 *
	 * @param
	 * @return Retourne null si l'objet n'existe pas sinon il affiche l'objet bien à
	 *         modifier
	 */
	@Override
	public Bien update(Bien bien) {
		// TODO Auto-generated method stub
		if (repo.existsById(bien.getId())) {
			return repo.save(bien);
		} else {
			return null;
		}
	}

	/**
	 * Methode : Pour supprimer un bien
	 * 
	 * @param
	 * @return Retourne vrai si l'objet a été supprimé sinon il affiche faux
	 */
	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Methode pour afficher un bien selon son id
	 * 
	 * @param
	 * @return Retourne l'objet si il existe sinon il affiche null
	 */
	@Override
	public Bien getById(Long id) {
		// TODO Auto-generated method stub

		try {
			return repo.findById(id).get();
		}

		catch (NoSuchElementException e) {
			return null;
		}
	}

	/**
	 * Methode : Pour afficher tout les biens
	 * 
	 * @param l'objet bien
	 * @return Retourne la liste de biens
	 */
	@Override
	public List<Bien> getAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean sellBien(Long id) {
		if (getById(id).isVendu() == false) {
			repo.sellBien(id);
			return true;
		} else {
			return false;
		}
	}

}
