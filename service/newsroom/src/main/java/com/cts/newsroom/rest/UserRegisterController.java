package com.cts.newsroom.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.newsroom.bean.User;
import com.cts.newsroom.bean.UserRegisterStatus;
import com.cts.newsroom.service.UserService;

@RestController
@RequestMapping("/register")
public class UserRegisterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterController.class);

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public UserRegisterStatus RegisterUser(@RequestBody User user) {

		LOGGER.info("Start RegisterUser()");

		String email = user.getEmail();
		User existingUser = userService.getUserByEmail(email);
		System.out.println(existingUser);

		UserRegisterStatus status = new UserRegisterStatus();
		status.setEmailExist(false);

		if (existingUser != null) {
			status.setEmailExist(true);
			status.setMessage("Email Already Exist");
			// return new UserRegisterStatus(true, "Email Already Exist");
		} else {
			userService.userRegister(user);
			status.setMessage("Successfully Registered");
		}

		LOGGER.info("end");
		return status;
	}
}
