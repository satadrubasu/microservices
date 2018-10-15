package com.ansa.userrole.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ansa.userrole.model.UserRole;

/**
 * @author Satadru Basu
 * Utilizing the JpaRepository feature to avail all persistence implementations
 * only to consider the right Entity and ID object
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Integer>{
	
	@Query(value="Select * from USERROLE where ROLENAME = :rName",nativeQuery = true)
	UserRole findByRoleName(@Param("rName") String roleName);

}
