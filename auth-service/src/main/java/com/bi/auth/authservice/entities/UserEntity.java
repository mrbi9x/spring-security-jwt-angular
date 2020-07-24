package com.bi.auth.authservice.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email") })
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank
    @Min(value = 4)
    @Max(value = 40)
    @Column(name = "username", nullable = false)
    private String username;
    @NotBlank
    @Email
    @Column(name = "email", nullable = false)
    private String email;
    @NotBlank
    @Min(value = 8)
    @Max(value = 50)
    @Column(name = "password", nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(columnDefinition = "user_id"), inverseJoinColumns = @JoinColumn(columnDefinition = "role_id"))
    @Size(min = 1, max = 5)
    private Set<RoleEntity> roles = new HashSet<>();
}