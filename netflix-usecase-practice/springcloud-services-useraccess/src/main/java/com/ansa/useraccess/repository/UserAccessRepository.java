package com.ansa.useraccess.repository;
 import org.springframework.data.jpa.repository.JpaRepository;

import com.ansa.useraccess.model.UserAccess;
 
/**
 * @author Satadru Basu
 *
 */
public interface UserAccessRepository extends JpaRepository<UserAccess,Long>{

}
