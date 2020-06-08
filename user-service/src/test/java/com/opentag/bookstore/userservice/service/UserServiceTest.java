package com.opentag.bookstore.userservice.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.opentag.bookstore.userservice.exception.UserAlreadyExistsException;
import com.opentag.bookstore.userservice.exception.UserDoesNotExistException;
import com.opentag.bookstore.userservice.model.dto.RegistrationDataDTO;
import com.opentag.bookstore.userservice.model.dto.UserDetailsDTO;
import com.opentag.bookstore.userservice.model.entity.User;
import com.opentag.bookstore.userservice.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private static final String LAST_NAME = "lastName";

	private static final String FIRST_NAME = "firstName";

	private static final String TEST_PASSWORD = "password";

	private static final String TEST_EMAIL = "email@email.com";

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;
	
	@Test(expected = UserAlreadyExistsException.class)
	public void register_whenUserAlreadyExist_exceptionIsThrown() {
		RegistrationDataDTO registrationData = new RegistrationDataDTO();
		registrationData.setEmail(TEST_EMAIL);
		Optional<User> user = Optional.of(new User());
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(user);
		userService.registerUser(registrationData);
	}

	@Test
	public void register_whenUserDoesntExist_registrationIsSuccessful() {
		RegistrationDataDTO registrationData = new RegistrationDataDTO();
		registrationData.setPassword(TEST_PASSWORD);
		registrationData.setEmail(TEST_EMAIL);
		registrationData.setFirstName(FIRST_NAME);
		registrationData.setLastName(LAST_NAME);
		Optional<User> user = Optional.empty();
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(user);
		userService.registerUser(registrationData);
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	public void getUserDetails_whenUserExists_userDetailsIsReturned() {
		Optional<User> user = Optional.of(new User());
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(user);
		UserDetailsDTO userDetailsDTO = userService.getUserById(TEST_EMAIL);
		assertNotNull(userDetailsDTO);
	}

	@Test(expected = UserDoesNotExistException.class)
	public void getUserDetails_whenUserDoesntExist_exceptionIsThrown() {
		Optional<User> user = Optional.empty();
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(user);
		userService.getUserById(TEST_EMAIL);
	}

}
