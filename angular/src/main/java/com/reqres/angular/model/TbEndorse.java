package com.reqres.angular.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_endorse")
public class TbEndorse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@Column(name = "vehicleId", length = 18)
	private Long vehicleId;

	@Column(name = "chassisNo", length = 50, nullable = false)
	private String chassisNo;

	@Column(name = "engineNo", length = 50, nullable = false)
	private String engineNo;

	@Column(name = "removalDate")
	private Date removalDate;

	@Column(name = "endExno", length = 50, nullable = false)
	private String endExno;

	@Column(name = "tradingPartner1", length = 50)
	private String tradingPartner1;

	@Column(name = "tradingPartner2", length = 50)
	private String tradingPartner2;

	@Column(name = "tradingPartner3", length = 50)
	private String tradingPartner3;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public Date getRemovalDate() {
		return removalDate;
	}

	public void setRemovalDate(Date removalDate) {
		this.removalDate = removalDate;
	}

	public String getEndExno() {
		return endExno;
	}

	public void setEndExno(String endExno) {
		this.endExno = endExno;
	}

	public String getTradingPartner1() {
		return tradingPartner1;
	}

	public void setTradingPartner1(String tradingPartner1) {
		this.tradingPartner1 = tradingPartner1;
	}

	public String getTradingPartner2() {
		return tradingPartner2;
	}

	public void setTradingPartner2(String tradingPartner2) {
		this.tradingPartner2 = tradingPartner2;
	}

	public String getTradingPartner3() {
		return tradingPartner3;
	}

	public void setTradingPartner3(String tradingPartner3) {
		this.tradingPartner3 = tradingPartner3;
	}
}
