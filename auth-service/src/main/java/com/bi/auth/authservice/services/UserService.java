package com.bi.auth.authservice.services;

import java.util.Optional;

import com.bi.auth.authservice.entities.UserEntity;

public interface UserService {
	Optional<UserEntity> getUserByUsername(String username);

	Optional<UserEntity> getUserById(Long id);

	String loginUser(String username, String password);
}