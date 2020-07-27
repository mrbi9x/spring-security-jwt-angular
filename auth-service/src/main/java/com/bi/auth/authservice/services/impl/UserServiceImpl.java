package com.bi.auth.authservice.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bi.auth.authservice.entities.UserEntity;
import com.bi.auth.authservice.repositories.UserRepository;
import com.bi.auth.authservice.services.JwtTokenProvider;
import com.bi.auth.authservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public Optional<UserEntity> getUserByUsername(final String username) {
		return this.userRepository.findByUsername(username);
	}

	@Override
	public Optional<UserEntity> getUserById(final Long id) {
		return this.userRepository.findById(id);
	}

	@Override
	public String loginUser(String username, String password) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtTokenProvider.generateToken(authentication);
	}

}