package com.ansa.users.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ansa.users.model.core.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value="Select * from USER u where LOWER(u.EMAIL) = LOWER(:email)",nativeQuery = true)
	public List<User> findByEmail(@Param("email") String email);
}
