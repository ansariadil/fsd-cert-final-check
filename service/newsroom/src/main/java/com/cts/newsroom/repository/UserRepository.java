package com.cts.newsroom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.newsroom.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	public User findByEmail(String email);
	public List<User> findByNameLike(@Param("name") String name);

}
