package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fr.adaming.enumeration.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Brias Guillaume
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client extends User {
	@Column(nullable = false)
	private Type type;

	@ManyToOne
	@JoinColumn(name = "id_agent")
	private Agent agent;

	@OneToMany(mappedBy = "client")
	private List<Bien> biens;

	public Client(String email, String fullName, String telephone, Type type) {
		super(email, fullName, telephone);
		this.type = type;
	}

	public Client(Integer id, String email, String fullName, String telephone, Type type) {
		super(id, email, fullName, telephone);
		this.type = type;
	}

	@Override
	public String toString() {
		return "Client [type=" + type + ", agent=" + agent + ", toString()=" + super.toString() + "]";
	}

}
