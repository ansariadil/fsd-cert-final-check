package com.cts.newsroom.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cts.newsroom.bean.Language;
import com.cts.newsroom.bean.Role;
import com.cts.newsroom.bean.User;
import com.cts.newsroom.bean.UserRegisterStatus;
import com.cts.newsroom.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void mGetUserByEmailSuccessTest() throws JsonProcessingException{
		LOGGER.info("Start mGetUserByEmailSuccessTest()");
		Role role = new Role(1, "Role");
		Language language = new Language(1, "Language", "Code");
		User userMock = new User(1, "Adil", "Adil Email", "Adil Password", role, "A", language);
		
		when(userService.getUserByEmail(userMock.getEmail())).thenReturn(userMock);
		
		assertEquals(userMock, userController.getUserByEmail(userMock.getEmail()));
		
		LOGGER.info("End mGetUserByEmailSuccessTest()");
	}
	
	@Test
	public void jGetUserByEmailSuccessTest() throws Exception{
		LOGGER.info("Start jGetUserByEmailSuccessTest()");
	
		mockMvc.perform(get("/user/{email}", "adil")).andExpect(status().isOk())
		.andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.email").value("adil")).andExpect(jsonPath("$.password").value("adil"))
		.andExpect(jsonPath("$.language.name").value("English")).andExpect(jsonPath("$.userStatus").value("A"));
				
		LOGGER.info("End jGetUserByEmailSuccessTest()");
	}
	
	@Test
	public void mGetUserByEmailFailedTest() throws JsonProcessingException{
		LOGGER.info("Start mGetUserByEmailFailedTest()");

		when(userService.getUserByEmail("email")).thenReturn(null);
		assertEquals(null, userController.getUserByEmail("email"));
		
		LOGGER.info("End mGetUserByEmailFailedTest()");
	}
	
	@Test
	public void jGetUserByEmailFailedTest() throws Exception{
		LOGGER.info("Start jGetUserByEmailFailedTest()");
	
		mockMvc.perform(get("/user/{email}", "adila"))
		.andExpect(status().isOk())
		.equals(null);

		LOGGER.info("End jGetUserByEmailFailedTest()");
	}
	
	@Test
	public void mSuccessfullUserRegisterTest(){
		LOGGER.info("Start mSuccessfullUserRegisterTest()");
		UserRegisterStatus expectedStatus= new UserRegisterStatus(false, "Successfully Registered");
		
		User user = new User(0, "test1", "test1", "test1", new Role(1, "NA"), "A", new Language(4, "English", "en"));
		
		when(userService.getUserByEmail(user.getEmail())).thenReturn(null);
		when(userService.userRegister(user)).thenReturn(user);
		
		UserRegisterStatus actualStatus= userController.RegisterUser(user);		
		assertEquals(expectedStatus, actualStatus);
	
		LOGGER.info("End mSuccessfullUserRegisterTest()");
	}
	
	@Test
	public void jSuccessfullUserRegisterTest() throws Exception{
		LOGGER.info("Start jSuccessfullUserRegisterTest()");
		
		String testJson = "{\"name\": \"test2\",\"email\": \"test2\",\"password\": \"test2\",\"language\": {\"id\": 4}}";
		//will be populated to db
		
		LOGGER.debug("test data:{}", testJson);
		mockMvc.perform(post("/user/register").content(testJson).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.emailExist").value(false));
		
		LOGGER.info("End jSuccessfullUserRegisterTest()");
	}
	
	@Test
	public void mFailedUserRegisterTest(){
		LOGGER.info("Start mFailedUserRegisterTest");
		
		User user = new User(0, "test1", "adil", "test1", new Role(1, "NA"), "A", new Language(4, "English", "en"));
		
		UserRegisterStatus expectedStatus= new UserRegisterStatus(true, "Email Already Exist");
		
		when(userService.getUserByEmail(user.getEmail())).thenReturn(user);
		when(userService.userRegister(user)).thenReturn(user);
		
		
		UserRegisterStatus actualStatus= userController.RegisterUser(user);
		
		assertEquals(expectedStatus, actualStatus);
		
		verify(userService, times(0)).userRegister(user);
		verify(userService, times(1)).getUserByEmail(user.getEmail());
		LOGGER.info("End: unSuccessfullSignupTest()");
	}
	
	@Test
	public void jFailedUserRegisterEmailAlreadyExistTest() throws Exception{
		LOGGER.info("Start jFailedUserRegisterEmailAlreadyExistTest()");
		
		String testJson = "{\"name\": \"adil\",\"email\": \"adil\",\"password\": \"1234567890\",\"language\": {\"id\": 4}}";
		
		LOGGER.debug("test data:{}", testJson);
		mockMvc.perform(post("/user/register").content(testJson).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.emailExist").value(true))
		.andExpect(jsonPath("$.message").value("Email Already Exist"));
		LOGGER.info("End jFailedUserRegisterEmailAlreadyExistTest()");
	}
	
//	@Test
//	public void jFailedUserRegisterNullObjectTest() throws Exception{
//		LOGGER.info("Start jFailedUserRegisterNullObjectTest()");
//
//		String testJson = null;
//		
//		LOGGER.debug("test data:{}", testJson);
//		mockMvc.perform(post("/user/register").content(testJson).contentType("application/json;charset=UTF-8"))
//		.andExpect(status().is5xxServerError())
//		.andDo(handler);
//		
//		LOGGER.info("End jFailedUserRegisterNullObjectTest()");
//	}
	
	
	@Test
	public void jFailedUserRegisterNullValuesTest() throws Exception{
		LOGGER.info("Start jFailedUserRegisterNullValuesTest()");

		String testJson = "{\"name\": \"\",\"email\": \"\",\"password\": \"\",\"language\": {\"id\": 0}}";
		
		LOGGER.debug("test data:{}", testJson);
		mockMvc.perform(post("/user/register").content(testJson).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.emailExist").value(true))
		.andExpect(jsonPath("$.message").value("Null Values Passed!"));
		
		LOGGER.info("End jFailedUserRegisterNullValuesTest()");
	}
	@Test
	public void jFailedUserRegisterNullNameTest() throws Exception{
		LOGGER.info("Start jFailedUserRegisterNullNameTest()");

		String testJson = "{\"name\": \"\",\"email\": \"test3\",\"password\": \"test3\",\"language\": {\"id\": 4}}";
		
		LOGGER.debug("test data:{}", testJson);
		mockMvc.perform(post("/user/register").content(testJson).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk())
//		.andExpect(jsonPath("$.emailExist").value(true))
		.andExpect(jsonPath("$.message").value("Null Values Passed!"));
		
		LOGGER.info("End jFailedUserRegisterNullNameTest()");
	}
	
	@Test
	public void jFailedUserRegisterNullPasswordTest() throws Exception{
		LOGGER.info("Start jFailedUserRegisterNullPasswordTest()");

		String testJson = "{\"name\": \"test3\",\"email\": \"test3\",\"password\": \"\",\"language\": {\"id\": 4}}";
		LOGGER.debug("test data:{}", testJson);
		mockMvc.perform(post("/user/register").content(testJson).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.emailExist").value(true))
		.andExpect(jsonPath("$.message").value("Null Values Passed!"));
		
		LOGGER.info("End jFailedUserRegisterNullPasswordTest()");
	}
	
	@Test
	public void jFailedUserRegisterNullEmailTest() throws Exception{
		LOGGER.info("Start jFailedUserRegisterNullEmailTest()");

		String testJson = "{\"name\": \"test3\",\"email\": \"\",\"password\": \"test3\",\"language\": {\"id\": 4}}";
		
		LOGGER.debug("test data:{}", testJson);
		mockMvc.perform(post("/user/register").content(testJson).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.emailExist").value(true))
		.andExpect(jsonPath("$.message").value("Null Values Passed!"));
		
		LOGGER.info("End jFailedUserRegisterNullEmailTest()");
	}
	
	@Test
	public void jFailedUserRegisterNullLanguageTest() throws Exception{
		LOGGER.info("Start jFailedUserRegisterNullLanguageTest()");

		String testJson = "{\"name\": \"test3\",\"email\": \"test3\",\"password\": \"test3\",\"language\": {\"id\": 0}}";
		
		LOGGER.debug("test data:{}", testJson);
		mockMvc.perform(post("/user/register").content(testJson).contentType("application/json;charset=UTF-8"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.emailExist").value(true))
		.andExpect(jsonPath("$.message").value("Null Values Passed!"));
		
		LOGGER.info("End jFailedUserRegisterNullLanguageTest()");
	}
	
	
	
	
	
	
	
	
	

}
