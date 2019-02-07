package com.cts.newsroom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cts.newsroom.bean.Role;
import com.cts.newsroom.repository.RoleRepository;

@Service
public class RoleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);
	
	private RoleRepository roleRepository;
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository){
		this.roleRepository=roleRepository;
	}
	
	
	@Transactional
	public Role getRoleByName(String role){
		LOGGER.info("Role service starts");
		return roleRepository.findRoleByName(role);
		
	}
}


