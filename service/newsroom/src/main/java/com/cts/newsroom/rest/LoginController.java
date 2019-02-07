package com.cts.newsroom.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.newsroom.bean.AuthenticationStatus;
import com.cts.newsroom.bean.User;
import com.cts.newsroom.jwt.security.JwtGenerator;
import com.cts.newsroom.service.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private UserService userservice;
	
	@Autowired
	private JwtGenerator jwtGenerator;

	@Autowired
	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<AuthenticationStatus> authenticate(@RequestBody User user) {
		String email = user.getEmail();
		LOGGER.debug("Email: {} ", email);
		String password = user.getPassword();
		LOGGER.debug("Password: {} ", password);

		String actualPassword = "";
		String actualEmail = "";
		AuthenticationStatus status = new AuthenticationStatus();
		status.setAuthenticated(false);
		status.setAdmin(false);
		User actualUser = userservice.getUserByEmail(email);
		
		LOGGER.info("actual User: {}", actualUser);
		
		if (actualUser != null) {
			actualPassword = actualUser.getPassword();

			if (actualUser.getRole().getName().equalsIgnoreCase("ad") && actualPassword.equals(password)) {
				status.setAdmin(true);
			}
			actualEmail = actualUser.getEmail();
			status.setUser(actualUser);
			status.setAuthenticated(actualEmail.equals(email));
			status.setAuthenticated(actualPassword.equals(password));

		}
		if(status.isAuthenticated()){
			status.setToken(jwtGenerator.generate(status.getUser()));
		}
		LOGGER.info("End {}", status);
		return new ResponseEntity<AuthenticationStatus>(status, HttpStatus.OK);
	}


//	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
//	public User getUser(@PathVariable("email") String email) throws JsonProcessingException {
//		LOGGER.info("Start");
//		User user = userservice.getUserByEmail(email);
//		System.out.println(user);
//		LOGGER.info("End");
//		return user;
//	}
}
