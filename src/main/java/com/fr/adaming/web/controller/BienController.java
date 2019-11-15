package com.fr.adaming.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.BienDtoCreate;

/**
 * @author bilel
 *
 */
@RequestMapping(path = "api/bien")
public interface BienController {

	@PostMapping(path = "/create")
	public String create(@Valid @RequestBody BienDtoCreate bienDtoCreate);

	@PutMapping(path = "/update")
	public String update(@Valid @RequestBody BienDto biendto);

	@PutMapping(path = "/{id}/sell-id/")
	public void sellBien(@PathVariable(name = "id") Long id);

	@GetMapping(path = "/{id}/get-id/")
	public Bien getById(@PathVariable(name = "id") Long id);

	@GetMapping(path = "/get-all")
	public List<Bien> getAll();

	@DeleteMapping(path = "/{id}/delete/")
	public String delete(@PathVariable(name = "id") Long id);
	
	@GetMapping(path = "/{idBien}/add-client/{idClient}")
	public String addClient(@PathVariable(name = "idBien") Long idBien, @PathVariable(name = "idClient") Integer idClient);

}
