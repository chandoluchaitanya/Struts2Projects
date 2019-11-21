package com.reqres.angular.bean;

import java.util.List;

import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbSeries;

public class VariantBeanForView {
	private String id;
	private String variantCode;
	private String variantName;
	private String variantDescription;
	private String brandId;
	private List<TbBrand> brands;
	private String seriesId;
	private List<TbSeries> serieses;
	private Boolean publishToOrder;
	private String lenOfChassisNo;
	private String lenOfEngineNo;
	private String prefixChassisNo;
	private String prefixEngineNo;
	private String statusId;
	private List<TbConfigStatus> statuses;
	private List<Colour> colours;

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

	public List<TbBrand> getBrands() {
		return brands;
	}

	public void setBrands(List<TbBrand> brands) {
		this.brands = brands;
	}

	public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	public List<TbSeries> getSerieses() {
		return serieses;
	}

	public void setSerieses(List<TbSeries> serieses) {
		this.serieses = serieses;
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

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public List<TbConfigStatus> getStatuses() {
		return statuses;
	}

	public void setStatuses(List<TbConfigStatus> statuses) {
		this.statuses = statuses;
	}

	public List<Colour> getColours() {
		return colours;
	}

	public void setColours(List<Colour> colours) {
		this.colours = colours;
	}
}
