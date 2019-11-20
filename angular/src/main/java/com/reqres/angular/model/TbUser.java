package com.reqres.angular.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
public class TbUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 30)
	private Long id;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "userName", length = 20, nullable = false)
	private String userName;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "firstName", length = 20, nullable = false)
	private String name;

	@Column(name = "lastName", length = 20, nullable = false)
	private String lastName;

	@Column(name = "active", length = 1, nullable = false)
	private int active;

	@Column(name = "createdDt", nullable = false)
	private Date createdDt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}
}
