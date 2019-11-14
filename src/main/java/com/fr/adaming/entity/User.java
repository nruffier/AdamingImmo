package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String fullName;
	private String telephone;
	
	public User(String email, String fullName, String telephone) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
	}
	
}
