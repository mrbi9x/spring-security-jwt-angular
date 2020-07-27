package com.bi.auth.authservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bi.auth.authservice.entities.RoleEntity;
import com.bi.auth.authservice.entities.RoleEnum;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByName(RoleEnum roleName);
}
