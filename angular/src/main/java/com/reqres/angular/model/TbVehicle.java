package com.reqres.angular.model;

import java.util.Date;

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
@Table(name = "tb_vehicle")
public class TbVehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "variantId", nullable = false)
	private TbVariant tbVariant;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "statusId", nullable = false)
	private TbConfigStatus tbConfigStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "colourId", nullable = false)
	private TbColour tbColour;

	@Column(name = "chassisNo", length = 50, nullable = false)
	private String chassisNo;

	@Column(name = "engineNo", length = 50, nullable = false)
	private String engineNo;

	@Column(name = "yearMade", length = 10, nullable = false)
	private Integer yearMade;

	@Column(name = "sequenceNo", length = 20, nullable = false)
	private String sequenceNo;

	@Column(name = "lotNo", length = 20, nullable = false)
	private String lotNo;

	@Column(name = "ckdImportDate", nullable = false)
	private Date ckdImportDate;

	@Column(name = "productionDate", nullable = false)
	private Date productionDate;

	@Column(name = "etd", nullable = false)
	private Date etd;

	@Column(name = "remark1", length = 50, nullable = false)
	private String remark1;

	@Column(name = "remark2", length = 50, nullable = false)
	private String remark2;

	@Column(name = "receiptNo", length = 50, nullable = false)
	private String receiptNo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicleTypeId", nullable = false)
	private TbVehicleType tbVehicleType;

	@Column(name = "isCompanyown")
	private Byte isCompanyown;
	
	@Column(name = "isReadyforSale")
	private Byte isReadyforSale;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TbVariant getTbVariant() {
		return tbVariant;
	}

	public void setTbVariant(TbVariant tbVariant) {
		this.tbVariant = tbVariant;
	}

	public TbConfigStatus getTbConfigStatus() {
		return tbConfigStatus;
	}

	public void setTbConfigStatus(TbConfigStatus tbConfigStatus) {
		this.tbConfigStatus = tbConfigStatus;
	}

	public TbColour getTbColour() {
		return tbColour;
	}

	public void setTbColour(TbColour tbColour) {
		this.tbColour = tbColour;
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

	public Integer getYearMade() {
		return yearMade;
	}

	public void setYearMade(Integer yearMade) {
		this.yearMade = yearMade;
	}

	public String getSequenceNo() {
		return sequenceNo;
	}

	public void setSequenceNo(String sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public Date getCkdImportDate() {
		return ckdImportDate;
	}

	public void setCkdImportDate(Date ckdImportDate) {
		this.ckdImportDate = ckdImportDate;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getEtd() {
		return etd;
	}

	public void setEtd(Date etd) {
		this.etd = etd;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public TbVehicleType getTbVehicleType() {
		return tbVehicleType;
	}

	public void setTbVehicleType(TbVehicleType tbVehicleType) {
		this.tbVehicleType = tbVehicleType;
	}
}
