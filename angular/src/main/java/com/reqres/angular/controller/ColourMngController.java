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
import com.reqres.angular.bean.ColourBeanForAdd;
import com.reqres.angular.bean.ColourMngBean;
import com.reqres.angular.bean.ColourMngBeanForView;
import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.service.ColourMngService;

@CrossOrigin(origins = "*")
@RestController
public class ColourMngController {

	@Autowired
	private ColourMngService colourMngService;

	@PostMapping(value = "/searchColourDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String searchColourDetails(@RequestBody ColourMngBean colourMngBean) {
		String response = null;
		try {
			PaginationUtilDTO dto = colourMngService.searchColourDetails(colourMngBean);
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping(value = "/colour/add", produces = MediaType.APPLICATION_JSON_VALUE)
	public String addNewColour() {
		String response = "";
		try {
			ColourBeanForAdd cb = colourMngService.addNewColourDetails();
			if (cb != null) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping(value = "/colour/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String saveColourDetails(@RequestBody ColourMngBean colourMngBean) {
		String status = null;
		try {
			status = colourMngService.saveColourDetails(colourMngBean);
		} catch (Exception e) {
			e.printStackTrace();
			status = "0";
		}
		return status;
	}
	
	@PostMapping(value = "/colour/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateColourDetails(@RequestBody ColourMngBean colourMngBean) {
		String status = null;
		try {
			status = colourMngService.updateColourDetails(colourMngBean);
		} catch (Exception e) {
			e.printStackTrace();
			status = "0";
		}
		return status;
	}

	@GetMapping(value = "/getColourDetailsById/{id}")
	public String getColourDetails(@PathVariable("id") String id) {
		String response = "";
		try {
			ColourMngBeanForView cmb = colourMngService.getColourDetails(id);
			if (cmb != null) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cmb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
