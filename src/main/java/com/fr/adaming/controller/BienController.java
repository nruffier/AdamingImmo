package com.fr.adaming.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Bien;

@RequestMapping(path = "adamingimmo/bien")
public interface BienController {
	
	@RequestMapping(path="/save", method=RequestMethod.POST)
	public Bien create(Bien bien);

	
	@RequestMapping(path="/update", method=RequestMethod.PUT)
	public Bien update(Bien bien);
	
	@RequestMapping(path="/delete", method=RequestMethod.DELETE)
	public boolean delete(Bien bien);

	@RequestMapping(path = "/{id}/find-id/", method = RequestMethod.GET)
	public Bien findById(Long id);

	@RequestMapping(path="/get-all", method=RequestMethod.GET)
	public List<Bien> getAll(Bien bien);

	@RequestMapping(path = "/{id}/sell-id/", method = RequestMethod.POST)
	public void sellBien(Long id);
	
	

}
