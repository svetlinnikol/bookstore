package com.opentag.bookstore.userservice.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * DTO used to log in the user.
 */
public class LoginDTO {

	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String userName) {
		this.email = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
