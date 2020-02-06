package com.reqres.angular.dto;

public class VehicleFilterDTO {
	private Long id;
	private String chassisNo;
	private String engineNo;
	private String modelName;
	private String colourName;
	private Integer yearMade;
	private String vehicleType;

	public VehicleFilterDTO(Long id, String chassisNo, String engineNo, String modelName, String colourName,
			Integer yearMade, String vehicleType) {
		this.id = id;
		this.chassisNo = chassisNo;
		this.engineNo = engineNo;
		this.modelName = modelName;
		this.colourName = colourName;
		this.yearMade = yearMade;
		this.vehicleType = vehicleType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getColourName() {
		return colourName;
	}

	public void setColourName(String colourName) {
		this.colourName = colourName;
	}

	public Integer getYearMade() {
		return yearMade;
	}

	public void setYearMade(Integer yearMade) {
		this.yearMade = yearMade;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
}
