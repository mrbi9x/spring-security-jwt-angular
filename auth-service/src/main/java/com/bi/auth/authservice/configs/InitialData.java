package com.bi.auth.authservice.configs;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bi.auth.authservice.entities.RoleEntity;
import com.bi.auth.authservice.entities.RoleEnum;
import com.bi.auth.authservice.entities.UserEntity;
import com.bi.auth.authservice.repositories.RoleRepository;
import com.bi.auth.authservice.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InitialData implements CommandLineRunner {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		log.info("--- Initial roles ---");
		Optional<RoleEntity> adminRole = this.roleRepo.findByName(RoleEnum.ADMIN);
		if (adminRole.isEmpty()) {
			adminRole = Optional.of(roleRepo.save(new RoleEntity(null, RoleEnum.ADMIN)));
		}
		Optional<RoleEntity> usernRole = this.roleRepo.findByName(RoleEnum.USER);
		if (usernRole.isEmpty()) {
			usernRole = Optional.of(roleRepo.save(new RoleEntity(null, RoleEnum.USER)));
		}
		Optional<RoleEntity> auditRole = this.roleRepo.findByName(RoleEnum.AUDIT);
		if (auditRole.isEmpty()) {
			auditRole = Optional.of(roleRepo.save(new RoleEntity(null, RoleEnum.AUDIT)));
		}
		roleRepo.findAll().forEach(roleE -> log.info(roleE.toString()));

		log.info("--- Initial user ---");
		Optional<UserEntity> adminUser = this.userRepo.findByUsername("admin");
		if (adminUser.isEmpty()) {
			adminUser = Optional.of(this.userRepo.save(new UserEntity(null, "admin", "admin@admin",
					passwordEncoder.encode("admin"), adminRole.stream().collect(Collectors.toSet()))));
		}
		Optional<UserEntity> normalUser = this.userRepo.findByUsername("user");
		if (normalUser.isEmpty()) {
			normalUser = Optional.of(this.userRepo.save(new UserEntity(null, "user", "user@user",
					passwordEncoder.encode("user"), usernRole.stream().collect(Collectors.toSet()))));
		}
		Optional<UserEntity> auditUser = this.userRepo.findByUsername("audit");
		if (auditUser.isEmpty()) {
			auditUser = Optional.of(this.userRepo.save(new UserEntity(null, "audit", "audit@audit",
					passwordEncoder.encode("audit"), auditRole.stream().collect(Collectors.toSet()))));
		}
		userRepo.findAll().forEach(userE -> log.info(userE.toString()));
		log.info("---  End initial ---");
	}

}
