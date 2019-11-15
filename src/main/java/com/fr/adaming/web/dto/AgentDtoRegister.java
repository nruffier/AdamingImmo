package com.fr.adaming.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Nicolas RUFFIER
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AgentDtoRegister {
	
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String fullName;
	@NotBlank
	@Pattern(regexp = "[0-9]{10}")
	private String telephone;
	@Size(min = 8, max = 16)
	@NotBlank
	private String pwd;
	@PastOrPresent
	private LocalDate dateRectrutement;
}
