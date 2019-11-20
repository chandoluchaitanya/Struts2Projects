package com.reqres.angular.bean;

public class VariantBean {
	private String id;
	private String variantCode;
	private String variantName;
	private String variantDescription;
	private String brandId;
	private String seriesId;
	private String publishToOrder;
	private String lenOfChassisNo;
	private String lenOfEngineNo;
	private String prefixChassisNo;
	private String prefixEngineNo;
	private String statusId;
	private Colour[] colours;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	public String getPublishToOrder() {
		return publishToOrder;
	}

	public void setPublishToOrder(String publishToOrder) {
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

	public Colour[] getColours() {
		return colours;
	}

	public void setColours(Colour[] colours) {
		this.colours = colours;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
}
