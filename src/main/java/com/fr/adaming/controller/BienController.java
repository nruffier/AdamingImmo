package com.fr.adaming.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;

@RequestMapping(path = "api/bien")
public interface BienController {
	
	@PostMapping(path="/save")
	public String create(@RequestBody BienDto biendto);
	
	@PutMapping(path="/update")
	public String update(@RequestBody BienDto biendto);
	
	@PutMapping(path = "/{id}/sell-id/")
	public void sellBien(@PathVariable(name = "id") Long id);

	@GetMapping(path = "/{id}/find-id/")
	public Bien findById(@PathVariable(name = "id") Long id);

	@GetMapping(path="/get-all")
	public List<Bien> getAll();
	
	@DeleteMapping(path="/delete")
	public String delete(BienDto biendto);


	
	

}
