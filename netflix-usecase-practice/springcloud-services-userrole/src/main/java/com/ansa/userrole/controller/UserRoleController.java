package com.ansa.userrole.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansa.userrole.jpa.repository.UserRoleRepository;
import com.ansa.userrole.model.UserRole;

@RestController
public class UserRoleController {
	
	@Autowired
	UserRoleRepository userRoleRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);
	private static final String version1 = "/v1";
	
	
	/**1. When running without eureka Or Gateway
	 *    curl -X GET -H 'Content-Type: application/json' -i http://localhost:7090/v1/userroles
	 *    
	 * 2. When running with Eureka and API Gateway(9090)	
	 *    curl -X GET -H 'Content-Type: application/json' -i http://localhost:9090/cloud-user-role-service/v1/userroles/
	 */
	@RequestMapping(value=version1+"/userroles",method=RequestMethod.GET)
	public List<UserRole> getUserRoles() {
		List<UserRole> result=null;
		logger.debug("Inside Userroles Controller");
		try{
			result = userRoleRepo.findAll();
		}catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return result;
	}
	
	/**1. When running without eureka Or Gateway
	 *    curl -X GET -H 'Content-Type: application/json' -i http://localhost:7090/v1/userroles/vendor
	 *    
	 * 2. When running with Eureka and API Gateway(9090)	
	 *    curl -X GET -H 'Content-Type: application/json' -i http://localhost:9090/cloud-user-role-service/v1/userroles/vendor
	 */
	@RequestMapping(value=version1+"/userroles/{rolename}",method=RequestMethod.GET)
	public UserRole getUserRoleByName(@PathVariable("rolename") String roleName) {
		UserRole result=null;
		logger.debug("Inside Userroles Controller");
		try{
			result = userRoleRepo.findByRoleName(roleName);
		}catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		return result;
	}

}
