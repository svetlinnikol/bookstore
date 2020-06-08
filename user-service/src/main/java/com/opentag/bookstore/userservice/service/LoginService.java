package com.opentag.bookstore.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.opentag.bookstore.userservice.exception.IncorrectUserNamePasswordException;
import com.opentag.bookstore.userservice.model.dto.LoginDTO;
import com.opentag.bookstore.userservice.model.entity.User;
import com.opentag.bookstore.userservice.repository.UserRepository;

/**
 * Service which handles the login process.
 */
@Service
public class LoginService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Autowired
	public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * Perform login by email and password.
	 * @param email - email of the user.
	 * @return true or false.
	 */
	public void login(LoginDTO loginData) {
		Optional<User> userOptional = userRepository.findUserByEmail(loginData.getEmail());
		if(userOptional.isPresent() && passwordEncoder.matches(loginData.getPassword(), userOptional.get().getPassword())) {
			return;
		}
		throw new IncorrectUserNamePasswordException();
	}

}