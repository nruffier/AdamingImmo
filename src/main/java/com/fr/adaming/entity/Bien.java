package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author bilel
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Bien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Double prix;

	@Column(nullable = false)
	private boolean vendu;

	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;

	public Bien(Double prix, boolean vendu) {
		super();
		this.prix = prix;
		this.vendu = vendu;
	}

	public Bien(Long id, Double prix, boolean vendu) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
	}

}
