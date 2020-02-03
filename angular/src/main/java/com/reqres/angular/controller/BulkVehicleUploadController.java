package com.reqres.angular.controller;

import java.util.List;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.reqres.angular.service.BulkVehicleUploadService;

@CrossOrigin(origins = "*")
@RestController
public class BulkVehicleUploadController {

	@Autowired
	private BulkVehicleUploadService bulkVehicleUploadService;

	@PostMapping(value = "/uploadFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		String response = "";
		try {
			List<String> responseList = bulkVehicleUploadService.uploadFile(file);
			if (!CollectionUtils.isEmpty(responseList)) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
