package com.opentag.bookstore.userservice.controller;

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
import com.opentag.bookstore.userservice.model.dto.LoginDTO;
import com.opentag.bookstore.userservice.service.LoginService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	private static final String EMPTY_STRING = "";

	private static final String TEST_PASSWORD = "password";

	private static final String TEST_EMAIL = "email@email.com";

	private static final String LOGIN_PATH = "/login/";

	@Mock
	private LoginService loginService;

	@InjectMocks
	private LoginController loginController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	public void login_whenEmailIsNotPresent_returnedStatusIsBadRequest() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setPassword(TEST_PASSWORD);
		String loginJson = writeAsJson(loginDTO);
		 mockMvc.perform(post(LOGIN_PATH).content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void login_whenEmailIsEmpty_returnedStatusIsBadRequest() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setPassword(TEST_PASSWORD);
		loginDTO.setEmail(EMPTY_STRING);
		String loginJson = writeAsJson(loginDTO);
		 mockMvc.perform(post(LOGIN_PATH).content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void login_whenPasswordIsEmpty_returnedStatusIsBadRequest() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(TEST_EMAIL);
		loginDTO.setPassword(EMPTY_STRING);
		String loginJson = writeAsJson(loginDTO);
		 mockMvc.perform(post(LOGIN_PATH).content(loginJson).contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isBadRequest());
	}

	@Test
	public void login_whenEmailAndPasswordAreCorrect_returnedStatusIsOk() throws Exception {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmail(TEST_EMAIL);
		loginDTO.setPassword(TEST_PASSWORD);
		String loginJson = writeAsJson(loginDTO);
		 mockMvc.perform(post(LOGIN_PATH).content(loginJson)
				 .contentType(MediaType.APPLICATION_JSON)
				 .accept(MediaType.APPLICATION_JSON))
		 .andExpect(status().isOk());
	}

	private String writeAsJson(LoginDTO loginDTO) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(loginDTO);
	}
}