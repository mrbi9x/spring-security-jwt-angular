package com.bi.auth.authservice.services.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bi.auth.authservice.entities.RoleEntity;
import com.bi.auth.authservice.entities.RoleEnum;
import com.bi.auth.authservice.entities.UserEntity;
import com.bi.auth.authservice.exceptions.EmailAlreadyExistsException;
import com.bi.auth.authservice.exceptions.InternalServerException;
import com.bi.auth.authservice.exceptions.UsernameAlreadyExistsException;
import com.bi.auth.authservice.repositories.RoleRepository;
import com.bi.auth.authservice.repositories.UserRepository;
import com.bi.auth.authservice.services.JwtTokenProvider;
import com.bi.auth.authservice.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@Override
	public UserEntity createUser(UserEntity userEntity) {
		if (this.userRepository.existsByUsername(userEntity.getUsername())) {
			throw new UsernameAlreadyExistsException("Username already exists.");
		}
		if (this.userRepository.existsByEmail(userEntity.getEmail())) {
			throw new EmailAlreadyExistsException("Email already exists.");
		}
		Optional<RoleEntity> userRole = this.roleRepository.findByName(RoleEnum.USER);
		if (userRole.isEmpty()) {
			throw new InternalServerException("Role User not be initial.");
		}
		userEntity.setRoles(Collections.singleton(userRole.get()));
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		return this.userRepository.save(userEntity);
	}

}