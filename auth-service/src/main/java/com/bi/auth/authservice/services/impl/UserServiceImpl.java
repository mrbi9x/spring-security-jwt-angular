package com.bi.auth.authservice.services.impl;

import java.util.Optional;

import com.bi.auth.authservice.entities.UserEntity;
import com.bi.auth.authservice.repositories.UserRepository;
import com.bi.auth.authservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserEntity> getUserByUsername(final String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public Optional<UserEntity> getUserById(final Long id) {
        return this.userRepository.findById(id);
    }

}