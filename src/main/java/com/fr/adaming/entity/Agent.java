package com.fr.adaming.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

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

	@Max(value = 16)
	@Min(value = 8)
	@Column(nullable = false)
	private String pwd;

	@PastOrPresent
	private LocalDate dateRectrutement;

	@OneToMany(mappedBy = "agent")
	private List<Client> clients;

	public Agent(@Email String email, String fullName,
			@Pattern(regexp = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d") String telephone, @Max(16) @Min(8) String pwd,
			@PastOrPresent LocalDate dateRectrutement) {
		super(email, fullName, telephone);
		this.pwd = pwd;
		this.dateRectrutement = dateRectrutement;
	}

}
