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
@Table(name = "tb_brand")
public class TbBrand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "statusID", nullable = false)
	private TbConfigStatus tbConfigStatus;

	@Column(name = "brandName", length = 100, nullable = false)
	private String brandName;

	@Column(name = "brandCode", length = 2, nullable = false)
	private String brandCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbConfigStatus getTbConfigStatus() {
		return tbConfigStatus;
	}

	public void setTbConfigStatus(TbConfigStatus tbConfigStatus) {
		this.tbConfigStatus = tbConfigStatus;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
}
