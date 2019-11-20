package com.reqres.angular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.reqres.angular.service.VariantService;

@CrossOrigin(origins = "*")
@RestController
public class VariantController {

	@Autowired
	private VariantService variantService;
}
