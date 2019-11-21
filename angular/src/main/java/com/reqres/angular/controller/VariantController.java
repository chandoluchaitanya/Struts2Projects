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
import com.reqres.angular.bean.VariantBean;
import com.reqres.angular.bean.VariantBeanForView;
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

	@PostMapping(value = "/searchVariantDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String searchUserDetails(@RequestBody VariantBean variantBean) {
		String response = null;
		try {
			PaginationUtilDTO dto = variantService.getVariantDetails(variantBean);
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping(value = "/getVariantDetailsById/{id}")
	public String getVariantDetails(@PathVariable("id") String id) {
		String response = "";
		try {
			VariantBeanForView vb = variantService.getVariantDetails(id);
			if (vb != null) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
