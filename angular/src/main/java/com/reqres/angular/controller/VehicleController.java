package com.reqres.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.bean.SearchVehicleBean;
import com.reqres.angular.bean.VehicleBeanForEdit;
import com.reqres.angular.bean.VehicleBeanForUpdate;
import com.reqres.angular.service.VehicleService;

@CrossOrigin(origins = "*")
@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping(value = "/searchVehicleDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String searchVehicleDetails(@RequestBody SearchVehicleBean searchVehicleBean) {
		String response = null;
		try {
			PaginationUtilDTO dto = vehicleService.getVehicleDetails(searchVehicleBean);
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping(value = "/getVehicleInfo/{id}")
	public String getVehicleInfo(@PathVariable("id") String id) {
		String response = "";
		try {
			VehicleBeanForEdit vb = vehicleService.getVehicleInfo(id);
			if (vb != null) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping(value = "/vehicle/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateVehicleDetails(@RequestBody VehicleBeanForUpdate bean) {
		String status = null;
		try {
			status = vehicleService.updateVehicleDetails(bean);
		} catch (Exception e) {
			e.printStackTrace();
			status = "0";
		}
		return status;
	}
}
