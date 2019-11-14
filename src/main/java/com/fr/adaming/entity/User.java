package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String fullName;
	@Pattern(regexp = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d")
	private String telephone;
	
	public User(@Email String email, String fullName,
			@Pattern(regexp = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d") String telephone) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
	}
	
}
