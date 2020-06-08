package com.opentag.bookstore.userservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opentag.bookstore.userservice.controller.errorhandle.SimpleFieldError;
import com.opentag.bookstore.userservice.model.dto.LoginDTO;
import com.opentag.bookstore.userservice.service.LoginService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Rest controller for user operations.
 */
@RestController
@RequestMapping("login")
public class LoginController {

	private final LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@ApiOperation(value = "Login")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "User successfully logged in."),
		@ApiResponse(code = 400, message = "There is a validation issue.", response = SimpleFieldError.class),
		@ApiResponse(code = 401, message = "Invalid username or password!")
	})
	@PostMapping("/")
	public ResponseEntity<Void> login(@Valid @RequestBody LoginDTO loginData) {
		loginService.login(loginData);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}