package com.bi.auth.authservice.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.bi.auth.authservice.entities.RoleEntity;
import com.bi.auth.authservice.entities.RoleEnum;
import com.bi.auth.authservice.entities.UserEntity;

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
		List<GrantedAuthority> authorities = buildSimpleGrantedAuthority(userEntity.getRoles());
		return new UserDetailsPrincipal(userEntity.getId(), userEntity.getUsername(), userEntity.getEmail(),
				userEntity.getPassword(), authorities);
	}

	public List<GrantedAuthority> buildSimpleGrantedAuthority(Set<RoleEntity> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (roles == null || roles.isEmpty()) {
			authorities.add(new SimpleGrantedAuthority(RoleEnum.USER.name()));
			return authorities;
		}
		for (RoleEntity roleEntity : roles) {
			authorities.add(new SimpleGrantedAuthority(roleEntity.getName().name()));
		}
		return authorities;
	}
}