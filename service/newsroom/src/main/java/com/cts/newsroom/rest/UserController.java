package com.cts.newsroom.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.newsroom.bean.User;
import com.cts.newsroom.bean.UserRegisterStatus;
import com.cts.newsroom.service.UserService;

@RestController
@RequestMapping("/rest/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	public User getUserByEmail(@PathVariable("email") String email) {
		LOGGER.info("Start");
		User user = userService.getUserByEmail(email);
		LOGGER.info("End");
		return user;
	}
	
	@RequestMapping(value = "userbyname/{name}", method = RequestMethod.GET)
	public List<User> getUserByName(@PathVariable("name") String name) {
		LOGGER.info("Start getUserByName");
		List<User> user = userService.getUserByName(name);
		LOGGER.info("End");
		return user;
	}

	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public List<User> getAllUser(){
		System.out.println("inside getAllUser() Controller");
		List<User> user = userService.getAllUser();
		return user;
	}
	
	@RequestMapping(value = "/toggleUser", method = RequestMethod.POST)
	public User toggleUser(@RequestBody User user){
		System.out.println("inside blacklistUser() Controller");
		return userService.toggleUser(user);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public UserRegisterStatus RegisterUser(@RequestBody User user) {
		
		LOGGER.info("Start RegisterUser()");
//		if(user == null){
//			return new UserRegisterStatus(true, "Null User Passed!");
//		}
		System.out.println(user);
		
		if(user.getEmail().equals("") || user.getName().equals("")
				|| user.getPassword().equals("") || user.getLanguage().getId() == 0){
			System.out.println("inside null values");
			return new UserRegisterStatus(true, "Null Values Passed!");
		}

		String email = user.getEmail();
		User existingUser = userService.getUserByEmail(email);
		System.out.println(existingUser);
		

		UserRegisterStatus status = new UserRegisterStatus();
		status.setEmailExist(false);
		
		if (existingUser != null) {
			status.setEmailExist(true);
			status.setMessage("Email Already Exist");
//			return new UserRegisterStatus(true, "Email Already Exist");
		} else{
			userService.userRegister(user);
			status.setMessage("Successfully Registered");
		}
		
		LOGGER.info("end");
		return status;
	}	
}
