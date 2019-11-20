package com.fr.adaming.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
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
	@NotNull
	@Email
	@ApiModelProperty(required = true, example = "email@email.fr")
	private String email;
	@NotBlank
	@NotNull
	@ApiModelProperty(required = true, example = "fullname")
	private String fullName;
	@NotBlank
	@Pattern(regexp = "[0-9]{10}")
	@ApiModelProperty(required = true, example = "0123456789")
	private String telephone;
	@Size(min = 8, max = 16)
	@NotBlank
	@ApiModelProperty(required = true, example = "azertyuiop")
	private String pwd;
	@PastOrPresent
	@ApiModelProperty(required = true, example = "0001-01-01")
	private LocalDate dateRectrutement;
}
