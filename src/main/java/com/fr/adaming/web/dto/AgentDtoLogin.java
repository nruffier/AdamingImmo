package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AgentDtoLogin {

	@NotBlank
	@Email
	private String email;
	@Size(min = 8, max = 16)
	@NotBlank
	private String pwd;
}
