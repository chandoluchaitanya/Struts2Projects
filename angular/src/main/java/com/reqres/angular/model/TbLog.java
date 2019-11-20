package com.reqres.angular.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_Log")
public class TbLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LogId", length = 30)
	private Long logId;
	@Column(name = "LogUserId", length = 30)
	private Long logUserId;
	@Column(name = "LogType", length = 20)
	private String logType;
	@Column(name = "Ip", length = 30)
	private String ipaddress;
	@Column(name = "LogTime")
	private Date logTime;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getLogUserId() {
		return logUserId;
	}

	public void setLogUserId(Long logUserId) {
		this.logUserId = logUserId;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}
