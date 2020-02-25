package com.reqres.angular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_customer")
public class TbCustomer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "genderId", nullable = false)
	private TbGender tbGender;

	@Column(name = "customerName", length = 50, nullable = false)
	private String customerName;

	@Column(name = "email", length = 50, nullable = false)
	private String email;

	@Column(name = "phoneNo", length = 50, nullable = false)
	private String phoneNo;

	@Column(name = "identificationNo", length = 50, nullable = false)
	private String identificationNo;

	@Column(name = "address", length = 250, nullable = false)
	private String address;

	@Column(name = "city", length = 50, nullable = false)
	private String city;

	@Column(name = "state", length = 30, nullable = false)
	private String state;

	@Column(name = "postCode", length = 10, nullable = false)
	private String postCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbGender getTbGender() {
		return tbGender;
	}

	public void setTbGender(TbGender tbGender) {
		this.tbGender = tbGender;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
