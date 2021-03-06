package com.bi.auth.authservice.payloads;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SigninRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
}
