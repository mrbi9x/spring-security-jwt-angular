package com.bi.auth.authservice.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {

	@NotBlank
	// @Size(min = 6, max = 50)
	private String username;
	@Email
	@NotBlank
	// @Size(max = 50)
	private String email;
	@NotBlank
	// @Size(min = 6, max = 50)
	private String password;

	@NotBlank
	private String rePassword;
}
