package com.events.org.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "admin_usr", nullable = false, unique = true, length = 255)
	private String adminUsr;

	@Column(nullable = false, length = 255)
	private String password;

	@Column(nullable = false, unique = true, length = 255)
	private String email;

	@Column(length = 50)
	private String phone;

	@Column(name = "full_name", nullable = false, length = 255)
	private String fullName;

	public Admin() {
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAdminUsr() {
		return adminUsr;
	}

	public void setAdminUsr(String adminUsr) {
		this.adminUsr = adminUsr;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
