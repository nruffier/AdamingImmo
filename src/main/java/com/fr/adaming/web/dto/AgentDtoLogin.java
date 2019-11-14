package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AgentDtoLogin {

	@NotBlank
	@Email
	private String email;
	@Max(value = 16)
	@Min(value = 8)
	@NotBlank
	private String pwd;
}
