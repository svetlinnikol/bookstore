package com.opentag.bookstore.userservice.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.opentag.bookstore.userservice.exception.IncorrectUserNamePasswordException;
import com.opentag.bookstore.userservice.model.dto.LoginDTO;
import com.opentag.bookstore.userservice.model.entity.User;
import com.opentag.bookstore.userservice.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

	private static final String OTHER_ENCRYPTED_PASSWORD = "otherEncryptedPassword";

	private static final String PASSWORD = "password";

	private static final String TEST_EMAIL = "email@email.com";

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private LoginService loginService;

	@Test
	public void login_whenUserDoesExistAndPasswordMatches_SuccessfulLoginAndNoException() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(TEST_EMAIL);
		loginDTO.setPassword(PASSWORD);
		User dbUser = new User();
		dbUser.setPassword(OTHER_ENCRYPTED_PASSWORD);
		Optional<User> user = Optional.of(dbUser);
		when(passwordEncoder.matches(PASSWORD, OTHER_ENCRYPTED_PASSWORD)).thenReturn(true);
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(user);
		loginService.login(loginDTO);
	}

	@Test(expected = IncorrectUserNamePasswordException.class)
	public void login_whenUserDoesNotExist_ExceptionIsThrown() {
		Optional<User> user = Optional.empty();
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(user);
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(TEST_EMAIL);
		loginDTO.setPassword(PASSWORD);
		loginService.login(loginDTO);
	}

	@Test(expected = IncorrectUserNamePasswordException.class)
	public void login_whenUserDoesExistAndPasswordDoesntMatch_ExceptionIsThrown() {
		User dbUser = new User();
		dbUser.setPassword(OTHER_ENCRYPTED_PASSWORD);
		Optional<User> user = Optional.of(dbUser);
		when(userRepository.findUserByEmail(TEST_EMAIL)).thenReturn(user);
		when(passwordEncoder.matches(PASSWORD, OTHER_ENCRYPTED_PASSWORD)).thenReturn(false);
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(TEST_EMAIL);
		loginDTO.setPassword(PASSWORD);
		loginService.login(loginDTO);
	}

}
