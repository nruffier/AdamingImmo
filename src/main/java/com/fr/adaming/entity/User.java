package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Nicolas RUFFIER
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false, name = "fullName")
	private String fullName;
	private String telephone;
	
	public User(String email, String fullName, String telephone) {
		super();
		this.email = email;
		this.fullName = fullName;
		this.telephone = telephone;
	}
	
}
