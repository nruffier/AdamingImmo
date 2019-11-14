package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import com.fr.adaming.enumeration.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client extends User {

	@Column(nullable = false)
	private Type type;

	@ManyToOne
	@JoinColumn(name = "id_agent")
	private Agent agent;

	@OneToMany(mappedBy = "client")
	private List<Bien> biens;

	public Client(@Email String email, String fullName,
			@Pattern(regexp = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d") String telephone, Type type) {
		super(email, fullName, telephone);
		this.type = type;
	}

}
