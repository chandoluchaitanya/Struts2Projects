package com.reqres.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.reqres.angular.service.BulkVehicleUploadService;

@CrossOrigin(origins = "*")
@RestController
public class BulkVehicleUploadController {

	@Autowired
	private BulkVehicleUploadService bulkVehicleUploadService;

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		String response = "";
		try {
			response = bulkVehicleUploadService.uploadFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
