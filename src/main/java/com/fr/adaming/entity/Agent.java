package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Agent extends User {
	
	@Max(value = 16)
	@Min(value = 8)
	private String pwd;
	
	@PastOrPresent
	private LocalDate dateRectrutement;
	
	@OneToMany(mappedBy = "agent")
	private List<Client> clients;

}
