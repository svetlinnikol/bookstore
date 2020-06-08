package com.opentag.bookstore.userservice.model.dto;

/**
 * DTO used for user details.
 */
public class UserDetailsDTO {

	private final String firstName;
	private final String lastName;
	
	public UserDetailsDTO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
}
