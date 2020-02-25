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

/**
 * @author chandoluchaitanya
 *
 */
@Entity
@Table(name = "tb_packinglist")
public class TbPackingList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@Column(name = "packingListNo", length = 50)
	private String packingListNo;

	@Column(name = "invoiceNo", length = 50)
	private String invoiceNo;

	@Column(name = "blDate")
	private Date blDate;

	@Column(name = "batchNo", length = 50)
	private String batchNo;

	@Column(name = "certificateOfOrigin")
	private Byte certificateOfOrigin;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vehicleId", nullable = false)
	private TbVehicle tbVehicle;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPackingListNo() {
		return packingListNo;
	}

	public void setPackingListNo(String packingListNo) {
		this.packingListNo = packingListNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getBlDate() {
		return blDate;
	}

	public void setBlDate(Date blDate) {
		this.blDate = blDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Byte getCertificateOfOrigin() {
		return certificateOfOrigin;
	}

	public void setCertificateOfOrigin(Byte certificateOfOrigin) {
		this.certificateOfOrigin = certificateOfOrigin;
	}

	public TbVehicle getTbVehicle() {
		return tbVehicle;
	}

	public void setTbVehicle(TbVehicle tbVehicle) {
		this.tbVehicle = tbVehicle;
	}

}
