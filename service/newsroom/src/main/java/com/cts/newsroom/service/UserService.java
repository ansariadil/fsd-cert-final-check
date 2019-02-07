package com.cts.newsroom.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.newsroom.bean.Role;
import com.cts.newsroom.bean.User;
import com.cts.newsroom.repository.UserRepository;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private UserRepository userRepository;
	private RoleService roleService;
	@Autowired
	private LanguageService languageService;

	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public User getUserByEmail(String email) {
		LOGGER.info("Start");
		User user = userRepository.findByEmail(email);
		LOGGER.info("{}", user);
		LOGGER.info("End ");
		return user;
	}
	
	@Transactional
	public List<User> getUserByName(String name) {
		LOGGER.info("Start");
		LOGGER.info("End");
		return userRepository.findByNameLike(name);
	}
	
	@Transactional
	public List<User> getAllUser() {
		LOGGER.info("Start");
		LOGGER.info("End");
		return userRepository.findAll();
	}
	
	@Transactional
	public User toggleUser(User user) {
		LOGGER.info("Start");
		LOGGER.info("{}",user);
		
		if(user.getUserStatus().equals("A")){
			user.setUserStatus("B");
		} else {
			user.setUserStatus("A");
		}
		
		LOGGER.info("{}",user);		
		return userRepository.save(user);
	}
	
	@Transactional
	public User updateUser(User user) {
		LOGGER.info("Start");
		LOGGER.info("End");
		return userRepository.save(user);
	}
	

	@Transactional
	public User userRegister(User user) {
		LOGGER.info("Starts");
		LOGGER.info("Set role As News Analyst");
		LOGGER.debug("User: {}", user);

		if (user.getRole() == null) {
//			Role role = new Role(1, "NA");
			user.setRole(new Role(1, "NA"));
			LOGGER.info("Role set");
		}
		
		if(user.getUserStatus() == null){
			user.setUserStatus("Y");
		}
		
		LOGGER.info("Signup done");
		LOGGER.info("end");

		return userRepository.save(user);
	}
}
