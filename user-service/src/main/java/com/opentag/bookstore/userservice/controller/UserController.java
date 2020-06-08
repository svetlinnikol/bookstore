package com.opentag.bookstore.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opentag.bookstore.userservice.controller.errorhandle.SimpleFieldError;
import com.opentag.bookstore.userservice.model.dto.RegistrationDataDTO;
import com.opentag.bookstore.userservice.model.dto.UserDetailsDTO;
import com.opentag.bookstore.userservice.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Rest controller for user operations.
 */
@RestController
@RequestMapping("users")
@Validated
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@ApiOperation(value = "Register new user.")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "New user created."),
		@ApiResponse(code = 400, message = "There is a validation issue.", response = SimpleFieldError.class),
		@ApiResponse(code = 409, message = "User already exists!")
	})
	@PostMapping("/register")
	public ResponseEntity<Void> registerUser(@Valid @RequestBody RegistrationDataDTO registrationData) {
		userService.registerUser(registrationData);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get user details")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Returns the specified user.", response = UserDetailsDTO.class),
		@ApiResponse(code = 404, message = "User not found.")
	})
	@GetMapping("/{userEmail}")
	public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable("userEmail") String userEmail) {
		UserDetailsDTO userDetails = userService.getUserById(userEmail);
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
}