package com.reqres.angular.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_config_status")
public class TbConfigStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "statusId", length = 18)
	private Long statusId;

	@Column(name = "statusDisplay", length = 50, nullable = false)
	private String statusDisplay;

	@Column(name = "statusDesc", length = 200, nullable = false)
	private String statusDesc;

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusDisplay() {
		return statusDisplay;
	}

	public void setStatusDisplay(String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
}
