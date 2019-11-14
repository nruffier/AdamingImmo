package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fr.adaming.enumeration.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity@Getter@Setter@NoArgsConstructor@ToString
public class Client extends User {
	
	@Column(nullable = false)
	private Type type;
	
	@ManyToOne
	@JoinColumn(name = "id_agent")
	private Agent agent;

	
	

}
