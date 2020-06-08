package com.opentag.bookstore.userservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentag.bookstore.userservice.controller.UserController;
import com.opentag.bookstore.userservice.model.dto.RegistrationDataDTO;
import com.opentag.bookstore.userservice.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private static final String USERS_REGISTER_PATH = "/users/register";

	private static final String SHORT_FIRST_NAME = "z";

	private static final String VERY_LONG_FIRST_NAME = "ThisIsAVeryLongFirstName";

	private static final String SHORT_PASSWORD = "short";

	private static final String INCORRECT_EMAIL = "email";

	private static final String TEST_LAST_NAME = "lastName";

	private static final String TEST_FIRST_NAME = "firstName";

	private static final String TEST_EMAIL = "email@email.com";

	private static final String TEST_PASSWORD = "password1";

	private static final String SHORT_LAST_NAME = "y";

	private static final String LONG_LAST_NAME = "ThisIsAVeryLongLastName";

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void registerUser_whenEverythingIsPresent_returnedStatusIsCreated() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setFirstName(TEST_FIRST_NAME);
		registrationDataDTO.setLastName(TEST_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isCreated());
	}

	@Test
	public void registerUser_whenEmailIsNotPresent_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setFirstName(TEST_FIRST_NAME);
		registrationDataDTO.setLastName(TEST_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenEmailIsNotCorrect_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setEmail(INCORRECT_EMAIL);
		registrationDataDTO.setFirstName(TEST_FIRST_NAME);
		registrationDataDTO.setLastName(TEST_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenPasswordIsNotPresent_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setFirstName(TEST_FIRST_NAME);
		registrationDataDTO.setLastName(TEST_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenPasswordIsTooShort_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setPassword(SHORT_PASSWORD);
		registrationDataDTO.setFirstName(TEST_FIRST_NAME);
		registrationDataDTO.setLastName(TEST_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenFirstNameIsNotPresent_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setLastName(TEST_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenFirstNameIsTooShort_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setFirstName(SHORT_FIRST_NAME);
		registrationDataDTO.setLastName(TEST_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenFirstNameIsTooLong_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setFirstName(VERY_LONG_FIRST_NAME);
		registrationDataDTO.setLastName(TEST_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenLastNameIsTooShort_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setFirstName(TEST_FIRST_NAME);
		registrationDataDTO.setLastName(SHORT_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenLastNameIsTooLong_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setFirstName(TEST_FIRST_NAME);
		registrationDataDTO.setLastName(LONG_LAST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void registerUser_whenLastNameIsNotPresent_returnedStatusIsBadRequest() throws Exception {
		RegistrationDataDTO registrationDataDTO = new RegistrationDataDTO();
		registrationDataDTO.setPassword(TEST_PASSWORD);
		registrationDataDTO.setEmail(TEST_EMAIL);
		registrationDataDTO.setFirstName(TEST_FIRST_NAME);
		String loginJson = writeAsJson(registrationDataDTO);
		 mockMvc.perform(post(USERS_REGISTER_PATH)
				 .content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void getUserDetails_whenTheRequestIsValid_returnedStatusIsOk() throws Exception {
		 mockMvc.perform(get("/users/" + TEST_EMAIL)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isOk());
	}

	private String writeAsJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}