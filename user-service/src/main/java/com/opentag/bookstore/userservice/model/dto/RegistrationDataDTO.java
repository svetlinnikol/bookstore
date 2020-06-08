package com.opentag.bookstore.userservice.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO used for user registration.
 */
public class RegistrationDataDTO {

	@NotNull
	@Size(min = 2, max = 15)
	private String firstName;
	@NotNull
	@Size(min = 2, max = 15)
	private String lastName;
	@NotNull
	@Size(min = 8, max = 30)
	@Pattern(regexp = "^(.+)@(.+)$", message = "The inputed email should be valid!")
	private String email;
	@NotNull
	@Size(min = 8, max = 30)
	private String password;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
