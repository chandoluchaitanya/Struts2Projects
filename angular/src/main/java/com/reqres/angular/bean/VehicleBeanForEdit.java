package com.reqres.angular.bean;

import java.util.List;

import com.reqres.angular.model.TbBrand;
import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbGender;
import com.reqres.angular.model.TbSeries;
import com.reqres.angular.model.TbVariant;
import com.reqres.angular.model.TbVehicleType;

public class VehicleBeanForEdit {

	private String id;
	private String brandId;
	private String seriesId;
	private String modelId;
	private String colourId;
	private String chassisNo;
	private String engineNo;
	private String lotNo;
	private String receiptNo;
	private String vehicleType;
	private String comOwnedVehicle;
	private String readyForSale;
	private String removalDate;
	private String endExNo;
	private String endTradingPartner1;
	private String endTradingPartner2;
	private String endTradingPartner3;
	private String pkgListNo;
	private String bLDate;
	private String invoiceNO;
	private String certificateOfOrigin;
	private String batchNo;
	private String customerName;
	private String gender;
	private String phoneNo;
	private String email;
	private String ic;
	private String address;
	private String city;
	private String state;
	private String postcode;

	private List<TbGender> genderList;
	private List<TbVehicleType> vehicleTypeList;
	private List<TbConfigStatus> statusList;
	private List<TbBrand> brandsList;
	private List<TbSeries> seriesList;
	private List<TbVariant> variantList;
	private List<Colour> colourList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getColourId() {
		return colourId;
	}

	public void setColourId(String colourId) {
		this.colourId = colourId;
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

	public String getLotNo() {
		return lotNo;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getComOwnedVehicle() {
		return comOwnedVehicle;
	}

	public void setComOwnedVehicle(String comOwnedVehicle) {
		this.comOwnedVehicle = comOwnedVehicle;
	}

	public String getReadyForSale() {
		return readyForSale;
	}

	public void setReadyForSale(String readyForSale) {
		this.readyForSale = readyForSale;
	}

	public String getRemovalDate() {
		return removalDate;
	}

	public void setRemovalDate(String removalDate) {
		this.removalDate = removalDate;
	}

	public String getEndExNo() {
		return endExNo;
	}

	public void setEndExNo(String endExNo) {
		this.endExNo = endExNo;
	}

	public String getEndTradingPartner1() {
		return endTradingPartner1;
	}

	public void setEndTradingPartner1(String endTradingPartner1) {
		this.endTradingPartner1 = endTradingPartner1;
	}

	public String getEndTradingPartner2() {
		return endTradingPartner2;
	}

	public void setEndTradingPartner2(String endTradingPartner2) {
		this.endTradingPartner2 = endTradingPartner2;
	}

	public String getEndTradingPartner3() {
		return endTradingPartner3;
	}

	public void setEndTradingPartner3(String endTradingPartner3) {
		this.endTradingPartner3 = endTradingPartner3;
	}

	public String getPkgListNo() {
		return pkgListNo;
	}

	public void setPkgListNo(String pkgListNo) {
		this.pkgListNo = pkgListNo;
	}

	public String getbLDate() {
		return bLDate;
	}

	public void setbLDate(String bLDate) {
		this.bLDate = bLDate;
	}

	public String getInvoiceNO() {
		return invoiceNO;
	}

	public void setInvoiceNO(String invoiceNO) {
		this.invoiceNO = invoiceNO;
	}

	public String getCertificateOfOrigin() {
		return certificateOfOrigin;
	}

	public void setCertificateOfOrigin(String certificateOfOrigin) {
		this.certificateOfOrigin = certificateOfOrigin;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
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

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public List<TbGender> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<TbGender> genderList) {
		this.genderList = genderList;
	}

	public List<TbVehicleType> getVehicleTypeList() {
		return vehicleTypeList;
	}

	public void setVehicleTypeList(List<TbVehicleType> vehicleTypeList) {
		this.vehicleTypeList = vehicleTypeList;
	}

	public List<TbConfigStatus> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<TbConfigStatus> statusList) {
		this.statusList = statusList;
	}

	public List<TbBrand> getBrandsList() {
		return brandsList;
	}

	public void setBrandsList(List<TbBrand> brandsList) {
		this.brandsList = brandsList;
	}

	public List<TbSeries> getSeriesList() {
		return seriesList;
	}

	public void setSeriesList(List<TbSeries> seriesList) {
		this.seriesList = seriesList;
	}

	public List<TbVariant> getVariantList() {
		return variantList;
	}

	public void setVariantList(List<TbVariant> variantList) {
		this.variantList = variantList;
	}

	public List<Colour> getColourList() {
		return colourList;
	}

	public void setColourList(List<Colour> colourList) {
		this.colourList = colourList;
	}
}
