package com.reqres.angular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.ListUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.reqres.angular.bean.LoginBean;
import com.reqres.angular.bean.PaginationUtilDTO;
import com.reqres.angular.bean.RegistrationBean;
import com.reqres.angular.bean.UserBean;
import com.reqres.angular.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody LoginBean loginBean) {
		List<String> responseList = null;
		String response = null;
		try {
			responseList = userService.validateLoginDetails(loginBean);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Generate Json Object
				ObjectMapper mapper = new ObjectMapper();
				ObjectNode objectNode = mapper.createObjectNode();
				if (!ListUtils.isEmpty(responseList)) {
					objectNode.put("username", loginBean.getUsername());
					objectNode.put("userID", responseList.get(0).toString());
					objectNode.put("error", "false");
				} else {
					objectNode.put("error", "true");
				}
				response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectNode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	@PostMapping(value = "/user/registration", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String registration(@RequestBody RegistrationBean registrationBean) {
		String status = null;
		try {
			status = userService.saveRegistrationDetails(registrationBean);
		} catch (Exception e) {
			e.printStackTrace();
			status = "0";
		}
		return status;
	}

	@PostMapping(value = "/searchUserDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String searchUserDetails(@RequestBody UserBean userBean) {
		String response = null;
		try {
			PaginationUtilDTO dto = userService.getUserDetails(userBean);
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@GetMapping(value = "/getUserDetailsById/{id}")
	public String getUserDetails(@PathVariable("id") String id) {
		String response = "";
		try {
			RegistrationBean rb = userService.getUserDetailsById(id);
			if (rb != null) {
				ObjectMapper mapper = new ObjectMapper();
				response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping(value = "/deleteUserDetailsById/{id}")
	public String deleteUserDetails(@PathVariable("id") String id) {
		String response = "";
		try {
			response = userService.deleteUserDetailsById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@PostMapping(value = "/updateUserDetails", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUserDetails(@RequestBody RegistrationBean registrationBean) {
		String status = null;
		try {
			status = userService.updateRegistrationDetails(registrationBean);
		} catch (Exception e) {
			e.printStackTrace();
			status = "0";
		}
		return status;
	}
}
