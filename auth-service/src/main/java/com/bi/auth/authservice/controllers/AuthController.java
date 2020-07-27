package com.bi.auth.authservice.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bi.auth.authservice.payloads.JwtAuthenticationResponse;
import com.bi.auth.authservice.payloads.SigninRequest;
import com.bi.auth.authservice.securities.JwtConfig;
import com.bi.auth.authservice.services.UserService;

@RestController
public class AuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private JwtConfig jwtConfig;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest signinRequest) {
		String token = userService.loginUser(signinRequest.getUsername(), signinRequest.getPassword());
		return ResponseEntity
				.ok(JwtAuthenticationResponse.builder().accessToken(token).tokenType(jwtConfig.getPrefix()).build());
	}

}
