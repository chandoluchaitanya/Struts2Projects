package com.reqres.angular.bean;

public class BulkVehicleUploadBean {
	private String vehicleModel;
	private String vehicleColour;
	private String vehiclePaintType;
	private String vehicleChassisNo;
	private String vehicleEngineNo;
	private String vehicleYearMade;
	private String vehicleSeqNo;
	private String vehicleLotNo;
	private String vehicleCkdImportDate;
	private String vehicleProductionDate;
	private String vehicleETD;
	private String vehicleRemark1;
	private String vehicleRemark2;
	private String vehicleReceiptNo;
	private String vehicleType;

	// Parameterized Constructor
	public BulkVehicleUploadBean(String vehicleModel, String vehicleColour, String vehiclePaintType,
			String vehicleChassisNo, String vehicleEngineNo, String vehicleYearMade, String vehicleSeqNo,
			String vehicleLotNo, String vehicleCkdImportDate, String vehicleProductionDate, String vehicleETD,
			String vehicleRemark1, String vehicleRemark2, String vehicleReceiptNo, String vehicleType) {
		this.vehicleModel = vehicleModel;
		this.vehicleColour = vehicleColour;
		this.vehiclePaintType = vehiclePaintType;
		this.vehicleChassisNo = vehicleChassisNo;
		this.vehicleEngineNo = vehicleEngineNo;
		this.vehicleYearMade = vehicleYearMade;
		this.vehicleSeqNo = vehicleSeqNo;
		this.vehicleLotNo = vehicleLotNo;
		this.vehicleCkdImportDate = vehicleCkdImportDate;
		this.vehicleProductionDate = vehicleProductionDate;
		this.vehicleETD = vehicleETD;
		this.vehicleRemark1 = vehicleRemark1;
		this.vehicleRemark2 = vehicleRemark2;
		this.vehicleReceiptNo = vehicleReceiptNo;
		this.vehicleType = vehicleType;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleColour() {
		return vehicleColour;
	}

	public void setVehicleColour(String vehicleColour) {
		this.vehicleColour = vehicleColour;
	}

	public String getVehiclePaintType() {
		return vehiclePaintType;
	}

	public void setVehiclePaintType(String vehiclePaintType) {
		this.vehiclePaintType = vehiclePaintType;
	}

	public String getVehicleChassisNo() {
		return vehicleChassisNo;
	}

	public void setVehicleChassisNo(String vehicleChassisNo) {
		this.vehicleChassisNo = vehicleChassisNo;
	}

	public String getVehicleEngineNo() {
		return vehicleEngineNo;
	}

	public void setVehicleEngineNo(String vehicleEngineNo) {
		this.vehicleEngineNo = vehicleEngineNo;
	}

	public String getVehicleYearMade() {
		return vehicleYearMade;
	}

	public void setVehicleYearMade(String vehicleYearMade) {
		this.vehicleYearMade = vehicleYearMade;
	}

	public String getVehicleSeqNo() {
		return vehicleSeqNo;
	}

	public void setVehicleSeqNo(String vehicleSeqNo) {
		this.vehicleSeqNo = vehicleSeqNo;
	}

	public String getVehicleLotNo() {
		return vehicleLotNo;
	}

	public void setVehicleLotNo(String vehicleLotNo) {
		this.vehicleLotNo = vehicleLotNo;
	}

	public String getVehicleCkdImportDate() {
		return vehicleCkdImportDate;
	}

	public void setVehicleCkdImportDate(String vehicleCkdImportDate) {
		this.vehicleCkdImportDate = vehicleCkdImportDate;
	}

	public String getVehicleProductionDate() {
		return vehicleProductionDate;
	}

	public void setVehicleProductionDate(String vehicleProductionDate) {
		this.vehicleProductionDate = vehicleProductionDate;
	}

	public String getVehicleETD() {
		return vehicleETD;
	}

	public void setVehicleETD(String vehicleETD) {
		this.vehicleETD = vehicleETD;
	}

	public String getVehicleRemark1() {
		return vehicleRemark1;
	}

	public void setVehicleRemark1(String vehicleRemark1) {
		this.vehicleRemark1 = vehicleRemark1;
	}

	public String getVehicleRemark2() {
		return vehicleRemark2;
	}

	public void setVehicleRemark2(String vehicleRemark2) {
		this.vehicleRemark2 = vehicleRemark2;
	}

	public String getVehicleReceiptNo() {
		return vehicleReceiptNo;
	}

	public void setVehicleReceiptNo(String vehicleReceiptNo) {
		this.vehicleReceiptNo = vehicleReceiptNo;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
