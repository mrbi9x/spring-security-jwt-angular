package com.bi.auth.authservice.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.bi.auth.authservice.entities.UserEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class ObjectMapperUtils {

    private static ObjectMapperUtils INSTANCE;

    private ObjectMapperUtils() {

    }

    public static ObjectMapperUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ObjectMapperUtils();
        }
        return INSTANCE;
    }

    public UserDetailsPrincipal mapToUserDetailsPrincipal(UserEntity userEntity) {
        List<GrantedAuthority> authorities = userEntity.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserDetailsPrincipal(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(),
                userEntity.getPassword(), authorities);
    }
}