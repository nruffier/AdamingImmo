package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Bien;
/**
 * @author bilel
 *
 */
public interface BienService {

	public Bien create(Bien bien);

	public Bien update(Bien bien);

	public boolean delete(Long id);

	public Bien getById(Long id);

	public List<Bien> getAll();

	public boolean sellBien(Long id);

}
