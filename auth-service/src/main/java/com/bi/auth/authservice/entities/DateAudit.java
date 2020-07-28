package com.bi.auth.authservice.entities;

import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "createAtTimestamp", "updatedAt",
		"updateAtTimestamp" }, allowGetters = true)
public abstract class DateAudit {

	@CreatedDate
	@Column(name = "create_at", nullable = false, updatable = false)
	private Date createdAt;

	@CreationTimestamp
	@Column(name = "create_at_timestamp", nullable = false, updatable = false)
	private Instant createAtTimestamp;

	@LastModifiedDate
	@Column(name = "update_at", nullable = false)
	private Date updatedAt;

	@UpdateTimestamp
	@Column(name = "update_at_timestamp", nullable = false)
	private Instant updateAtTimestamp;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Instant getCreateAtTimestamp() {
		return createAtTimestamp;
	}

	public void setCreateAtTimestamp(Instant createAtTimestamp) {
		this.createAtTimestamp = createAtTimestamp;
	}

	public Instant getUpdateAtTimestamp() {
		return updateAtTimestamp;
	}

	public void setUpdateAtTimestamp(Instant updateAtTimestamp) {
		this.updateAtTimestamp = updateAtTimestamp;
	}

}
