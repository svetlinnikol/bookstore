package com.opentag.bookstore.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.opentag.bookstore.userservice.exception.UserAlreadyExistsException;
import com.opentag.bookstore.userservice.exception.UserDoesNotExistException;
import com.opentag.bookstore.userservice.model.dto.RegistrationDataDTO;
import com.opentag.bookstore.userservice.model.dto.UserDetailsDTO;
import com.opentag.bookstore.userservice.model.entity.User;
import com.opentag.bookstore.userservice.repository.UserRepository;

/**
 * Service for CRUD operations for the user.
 */
@Service
public class UserService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Create new user.
	 * @param registrationData - the data needed to register the user.
	 */
	public void registerUser(RegistrationDataDTO registrationData) {
		if (userExists(registrationData.getEmail())) {
			throw new UserAlreadyExistsException();
		}
		User user = createUserEntity(registrationData);
		userRepository.save(user);
	}

	/**
	 * Retrieve user details by email.
	 * 
	 * @param email - email of the user.
	 * @return UserDetails containing user details.
	 */
	public UserDetailsDTO getUserById(String email) {
		Optional<User> user = userRepository.findUserByEmail(email);
		if(user.isPresent()) {
			return new UserDetailsDTO(user.get().getFirstName(), user.get().getLastName());
		}
		throw new UserDoesNotExistException();
	}

	private boolean userExists(String email) {
		return userRepository.findUserByEmail(email).isPresent();
	}

	private User createUserEntity(RegistrationDataDTO registrationData) {
		User user = new User();
		user.setFirstName(registrationData.getFirstName());
		user.setLastName(registrationData.getLastName());
		user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
		user.setEmail(registrationData.getEmail());
		user.setRole(1);
		return user;
	}
}
