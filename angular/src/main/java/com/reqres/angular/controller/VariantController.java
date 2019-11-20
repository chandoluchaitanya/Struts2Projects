package com.reqres.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reqres.angular.bean.VariantBean;
import com.reqres.angular.service.VariantService;

@CrossOrigin(origins = "*")
@RestController
public class VariantController {

	@Autowired
	private VariantService variantService;

	@PostMapping(value = "/variant/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveVariant(@RequestBody VariantBean variantBean) {
		String status = null;
		try {
			status = variantService.saveVariantDetails(variantBean);
		} catch (Exception e) {
			e.printStackTrace();
			status = "0";
		}
		return status;
	}

	@PostMapping(value = "/variant/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateVariant(@RequestBody VariantBean variantBean) {
		String status = null;
		try {
			status = variantService.updateVariantDetails(variantBean);
		} catch (Exception e) {
			e.printStackTrace();
			status = "0";
		}
		return status;
	}
}
