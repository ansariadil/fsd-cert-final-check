package com.cts.newsroom.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.newsroom.bean.AuthenticationStatus;
import com.cts.newsroom.bean.Language;
import com.cts.newsroom.bean.Role;
import com.cts.newsroom.bean.User;
import com.cts.newsroom.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginControllerTest.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private LoginController loginController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void mAuthenticateSuccessTest() {
		LOGGER.info("Start mAuthenticateSuccessTest()");
		User user = new User("Adil Email", "Adil Password");

		Role role = new Role(1, "Role");
		Language language = new Language(1, "Language", "Code");
		User userMock = new User(1, "Adil", "Adil Email", "Adil Password", role, "A", language);

		when(userService.getUserByEmail(user.getEmail())).thenReturn(userMock);
		ResponseEntity<AuthenticationStatus> status = loginController.authenticate(user);

		assertEquals(true, status.getBody().isAuthenticated());

		LOGGER.info("End mAuthenticateSuccessTest");
	}

	@Test
	public void jAuthenticateSuccessTest() throws Exception {
		LOGGER.info("Start jAuthenticateSuccessTest()");
		String TEST_DATA = "{\"email\":\"adil\"" + "," + "\"password\":\"adil\"}";
		LOGGER.debug("test data:{}", TEST_DATA);
		mockMvc.perform(post("/login/authenticate").content(TEST_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.authenticated").value(true));
		LOGGER.info("End jAuthenticateSuccessTest()");
	}

	@Test
	public void mAuthenticateFailedTest() {
		LOGGER.info("Start mAuthenticateFailedTest()");

		User user = new User("Wrong Email", "Wrong Password");

		// Role role = new Role(1, "Role");
		// Language language = new Language(1, "Language", "Code");
		// User userMock = new User(1, "Adil", "Adil Email", "Adil Password",
		// role, "A", language);

		when(userService.getUserByEmail(user.getEmail())).thenReturn(null);
		AuthenticationStatus status = loginController.authenticate(user).getBody();

		assertEquals(false, status.isAuthenticated());
		LOGGER.info("End");
	}

	@Test
	public void jAuthenticateFailedTest() throws Exception {
		LOGGER.info("Start jAuthenticateFailedTest()");
		String TEST_DATA = "{\"email\":\"user\"" + "," + "\"password\":\"12345\"}";
		LOGGER.debug("test data:{}", TEST_DATA);
		mockMvc.perform(post("/login/authenticate").content(TEST_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.authenticated").value(false));
		LOGGER.info("End: loginControllerTestForUnsuccessfullLogin()");
	}

	@Test
	public void mAuthenticateFailedForNullEmail() {
		LOGGER.info("Start authenticateFailedForNullEmail()");

		User user = new User();
		user.setPassword("adil");

		when(userService.getUserByEmail(user.getEmail())).thenReturn(null);
		AuthenticationStatus status = loginController.authenticate(user).getBody();

		assertEquals(true, status.isAuthenticated() == false);
		LOGGER.info("End");
	}

	@Test
	public void jAuthenticateFailedForNullEmail() throws Exception {
		LOGGER.info("Start jAuthenticateFailedForNullEmail()");
		String TEST_DATA = "{ \"password\":\"12345\"}";
		LOGGER.debug("test data:{}", TEST_DATA);
		mockMvc.perform(post("/login/authenticate").content(TEST_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.authenticated").value(false));
		LOGGER.info("End: loginControllerTestForNullEmail()");
	}

	@Test
	public void mAuthenticateFailedForNullPassword() {
		LOGGER.info("Start unsuccessfullyLoginForNullPassword()");

		User user = new User();
		user.setEmail("user");

		Role role = new Role(1, "Role");
		Language language = new Language(1, "Language", "Code");
		User userMock = new User(1, "Adil", "Adil Email", "Adil Password", role, "A", language);

		when(userService.getUserByEmail(user.getEmail())).thenReturn(userMock);
		AuthenticationStatus status = loginController.authenticate(user).getBody();

		assertEquals(false, status.isAuthenticated());
		LOGGER.info("End");
	}

	@Test
	public void jAuthenticateFailedForNullPassword() throws Exception {

		LOGGER.info("Start jAuthenticateFailedForNullPassword()");
		String TEST_DATA = "{ \"email\":\"user\"}";
		LOGGER.debug("test data:{}", TEST_DATA);
		mockMvc.perform(post("/login/authenticate").content(TEST_DATA).contentType("application/json;charset=UTF-8"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.authenticated").value(false));
		LOGGER.info("End: loginControllerTestForNullPassword()");
	}
	
//	@Test
//	public void mGetUserSuccessTest() throws JsonProcessingException{
//		LOGGER.info("Start mGetUserSuccessTest()");
//		Role role = new Role(1, "Role");
//		Language language = new Language(1, "Language", "Code");
//		User userMock = new User(1, "Adil", "Adil Email", "Adil Password", role, "A", language);
//		
//		when(userService.getUserByEmail(userMock.getEmail())).thenReturn(userMock);
//		
//		assertEquals(userMock, loginController.getUser(userMock.getEmail()));
//		
//		LOGGER.info("End mGetUserSuccessTest()");
//	}
//	
//	@Test
//	public void jGetUserSuccessTest() throws Exception{
//		LOGGER.info("Start jGetUserSuccessTest()");
//	
//		mockMvc.perform(get("/login/{email}", "adil")).andExpect(status().isOk())
//		.andExpect(content().contentType("application/json;charset=UTF-8"))
//		.andExpect(jsonPath("$.email").value("adil")).andExpect(jsonPath("$.password").value("adil"))
//		.andExpect(jsonPath("$.language.name").value("English")).andExpect(jsonPath("$.userStatus").value("A"));
//				
//		LOGGER.info("End jGetUserSuccessTest()");
//	}
//	
//	@Test
//	public void mGetUserFailedTest() throws JsonProcessingException{
//		LOGGER.info("Start mGetUserFailedTest()");
//
//		when(userService.getUserByEmail("email")).thenReturn(null);
//		assertEquals(null, loginController.getUser("email"));
//		
//		LOGGER.info("End mGetUserTest()");
//	}
//	
//	@Test
//	public void jGetUserFailedTest() throws Exception{
//		LOGGER.info("Start jGetUserSuccessTest()");
//	
//		mockMvc.perform(get("/login/{email}", "adila"))
//		.andExpect(status().isOk())
//		.equals(null);
//
//		LOGGER.info("End jGetUserSuccessTest()");
//	}

}
