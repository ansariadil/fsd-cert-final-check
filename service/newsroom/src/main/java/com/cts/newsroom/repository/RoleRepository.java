package com.cts.newsroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.newsroom.bean.Role;


public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findRoleByName(String role);
	Role findById(int Id);
}
