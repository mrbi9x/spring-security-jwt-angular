package com.bi.auth.authservice.entities;

import java.util.HashSet;
import java.util.Objects;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "username", "email" }))
public class UserEntity extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	@Column(name = "username", nullable = false)
	private String username;

	@NotBlank
	@Email
	@Column(name = "email", nullable = false)
	private String email;

	@NotBlank
	@Column(name = "password", nullable = false)
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Size(min = 1, max = 5)
	@EqualsAndHashCode.Exclude
	private Set<RoleEntity> roles = new HashSet<>();

	public UserEntity() {
	}

	public UserEntity(Long id, @NotBlank String username, @NotBlank @Email String email, @NotBlank String password,
			@Size(min = 1, max = 5) Set<RoleEntity> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof UserEntity))
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", roles=" + roles + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()=" + getUpdatedAt()
				+ ", getCreateAtTimestamp()=" + getCreateAtTimestamp() + ", getUpdateAtTimestamp()="
				+ getUpdateAtTimestamp() + "]";
	}

}