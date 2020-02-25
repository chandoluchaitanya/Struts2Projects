package com.reqres.angular.bean;

import java.util.List;

import com.reqres.angular.model.TbConfigStatus;
import com.reqres.angular.model.TbEndorse;
import com.reqres.angular.model.TbGender;
import com.reqres.angular.model.TbPackingList;
import com.reqres.angular.model.TbVehicle;
import com.reqres.angular.model.TbVehicleType;

public class VehicleBeanForEdit {

	private TbVehicle vehicle;
	private TbEndorse endorse;
	private TbPackingList packingList;
	private TbCustomerVehicleOwnedInfo customerVehicleOwnedInfo;
	private List<TbGender> genderList;
	private List<TbVehicleType> vehicleTypeList;
	private List<TbConfigStatus> statusList;

	public TbVehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(TbVehicle vehicle) {
		this.vehicle = vehicle;
	}

	public TbEndorse getEndorse() {
		return endorse;
	}

	public void setEndorse(TbEndorse endorse) {
		this.endorse = endorse;
	}

	public TbPackingList getPackingList() {
		return packingList;
	}

	public void setPackingList(TbPackingList packingList) {
		this.packingList = packingList;
	}

	public TbCustomerVehicleOwnedInfo getCustomerVehicleOwnedInfo() {
		return customerVehicleOwnedInfo;
	}

	public void setCustomerVehicleOwnedInfo(TbCustomerVehicleOwnedInfo customerVehicleOwnedInfo) {
		this.customerVehicleOwnedInfo = customerVehicleOwnedInfo;
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
}
