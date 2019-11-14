package com.fr.adaming.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class BienDto {

	@NotBlank
	private Long id;

	@NotBlank
	@Positive
	private Double prix;

	@NotNull
	private boolean vendu;

}
