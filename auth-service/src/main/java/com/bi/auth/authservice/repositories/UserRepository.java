package com.bi.auth.authservice.repositories;

import java.util.Optional;

import com.bi.auth.authservice.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}