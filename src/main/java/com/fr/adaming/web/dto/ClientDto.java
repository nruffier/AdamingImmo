package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fr.adaming.enumeration.Type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Brias Gullaume
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientDto {

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String fullName;

	@NotBlank
	@Pattern(regexp = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d")
	private String telephone;

	@NotNull
	private Type type;

}
