package com.bi.auth.authservice.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Enumerated(EnumType.STRING)
	private RoleEnum name;

	public RoleEntity() {
	}

	public RoleEntity(Long id, RoleEnum name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleEnum getName() {
		return name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RoleEntity))
			return false;
		RoleEntity other = (RoleEntity) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RoleEntity [id=" + id + ", name=" + name + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()="
				+ getUpdatedAt() + ", getCreateAtTimestamp()=" + getCreateAtTimestamp() + ", getUpdateAtTimestamp()="
				+ getUpdateAtTimestamp() + "]";
	}

}