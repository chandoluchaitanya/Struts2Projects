package com.reqres.angular.bean;

public class VehicleBeanForUpdate {

	private String id;
	private String brandId;
	private String seriesId;
	private String modelId;
	private String colourId;
	private String chassisNo;
	private String engineNo;
	private VehicleDetailBean vehicleDetailsGroup;
	private VehicleTypeBean vehicleTypeGroup;
	private EndorsementBean endorsementGroup;
	private ShippingInfoBean shippingInfoGroup;
	private CustomerInfoBean customerInfoGroup;

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

	public VehicleDetailBean getVehicleDetailsGroup() {
		return vehicleDetailsGroup;
	}

	public void setVehicleDetailsGroup(VehicleDetailBean vehicleDetailsGroup) {
		this.vehicleDetailsGroup = vehicleDetailsGroup;
	}

	public VehicleTypeBean getVehicleTypeGroup() {
		return vehicleTypeGroup;
	}

	public void setVehicleTypeGroup(VehicleTypeBean vehicleTypeGroup) {
		this.vehicleTypeGroup = vehicleTypeGroup;
	}

	public EndorsementBean getEndorsementGroup() {
		return endorsementGroup;
	}

	public void setEndorsementGroup(EndorsementBean endorsementGroup) {
		this.endorsementGroup = endorsementGroup;
	}

	public ShippingInfoBean getShippingInfoGroup() {
		return shippingInfoGroup;
	}

	public void setShippingInfoGroup(ShippingInfoBean shippingInfoGroup) {
		this.shippingInfoGroup = shippingInfoGroup;
	}

	public CustomerInfoBean getCustomerInfoGroup() {
		return customerInfoGroup;
	}

	public void setCustomerInfoGroup(CustomerInfoBean customerInfoGroup) {
		this.customerInfoGroup = customerInfoGroup;
	}
}
