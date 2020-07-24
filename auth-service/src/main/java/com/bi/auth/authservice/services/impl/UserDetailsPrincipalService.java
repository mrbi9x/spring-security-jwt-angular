package com.bi.auth.authservice.services.impl;

import com.bi.auth.authservice.dtos.ObjectMapperUtils;
import com.bi.auth.authservice.entities.UserEntity;
import com.bi.auth.authservice.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsPrincipalService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found User with username: " + username));
        return ObjectMapperUtils.getInstance().mapToUserDetailsPrincipal(userEntity);
    }

}