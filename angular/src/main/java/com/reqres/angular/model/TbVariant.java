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
@Table(name = "tb_variant")
public class TbVariant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 18)
	private Long id;

	@Column(name = "variantCode", length = 50, nullable = false)
	private String variantCode;

	@Column(name = "variantName", length = 50, nullable = false)
	private String variantName;

	@Column(name = "variantDescription", length = 50, nullable = false)
	private String variantDescription;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brandID", nullable = false)
	private TbBrand brandId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seriesID", nullable = false)
	private TbSeries seriesId;

	@Column(name = "publishToOrder")
	private Boolean publishToOrder;

	@Column(name = "lenOfChassisNo", length = 50, nullable = false)
	private String lenOfChassisNo;

	@Column(name = "lenOfEngineNo", length = 50, nullable = false)
	private String lenOfEngineNo;

	@Column(name = "prefixChassisNo", length = 50, nullable = false)
	private String prefixChassisNo;

	@Column(name = "prefixEngineNo", length = 50, nullable = false)
	private String prefixEngineNo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "statusId", nullable = false)
	private TbConfigStatus tbConfigStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVariantCode() {
		return variantCode;
	}

	public void setVariantCode(String variantCode) {
		this.variantCode = variantCode;
	}

	public String getVariantName() {
		return variantName;
	}

	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}

	public String getVariantDescription() {
		return variantDescription;
	}

	public void setVariantDescription(String variantDescription) {
		this.variantDescription = variantDescription;
	}

	public TbBrand getBrandId() {
		return brandId;
	}

	public void setBrandId(TbBrand brandId) {
		this.brandId = brandId;
	}

	public TbSeries getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(TbSeries seriesId) {
		this.seriesId = seriesId;
	}

	public Boolean getPublishToOrder() {
		return publishToOrder;
	}

	public void setPublishToOrder(Boolean publishToOrder) {
		this.publishToOrder = publishToOrder;
	}

	public String getLenOfChassisNo() {
		return lenOfChassisNo;
	}

	public void setLenOfChassisNo(String lenOfChassisNo) {
		this.lenOfChassisNo = lenOfChassisNo;
	}

	public String getLenOfEngineNo() {
		return lenOfEngineNo;
	}

	public void setLenOfEngineNo(String lenOfEngineNo) {
		this.lenOfEngineNo = lenOfEngineNo;
	}

	public String getPrefixChassisNo() {
		return prefixChassisNo;
	}

	public void setPrefixChassisNo(String prefixChassisNo) {
		this.prefixChassisNo = prefixChassisNo;
	}

	public String getPrefixEngineNo() {
		return prefixEngineNo;
	}

	public void setPrefixEngineNo(String prefixEngineNo) {
		this.prefixEngineNo = prefixEngineNo;
	}

	public TbConfigStatus getTbConfigStatus() {
		return tbConfigStatus;
	}

	public void setTbConfigStatus(TbConfigStatus tbConfigStatus) {
		this.tbConfigStatus = tbConfigStatus;
	}
}
