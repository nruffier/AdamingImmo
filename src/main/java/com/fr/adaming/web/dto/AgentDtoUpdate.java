package com.fr.adaming.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class AgentDtoUpdate {
	
	@NotNull
	private Integer Id;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String fullName;
	@NotBlank
	@Pattern(regexp = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d")
	private String telephone;
	@Max(value = 16)
	@Min(value = 8)
	@NotBlank
	private String pwd;
	@PastOrPresent
	private LocalDate dateRectrutement;

}
