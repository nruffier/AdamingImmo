package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agent extends User {

	@Column(nullable = false)
	private String pwd;

	private LocalDate dateRectrutement;

	@OneToMany(mappedBy = "agent")
	private List<Client> clients;

	public Agent(String email, String fullName, String telephone, String pwd, LocalDate dateRectrutement) {
		super(email, fullName, telephone);
		this.pwd = pwd;
		this.dateRectrutement = dateRectrutement;
	}

}
