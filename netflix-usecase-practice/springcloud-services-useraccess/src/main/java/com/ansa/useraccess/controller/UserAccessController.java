package com.ansa.useraccess.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansa.useraccess.feignclient.UserRoleService;
import com.ansa.useraccess.feignclient.UserService;
import com.ansa.useraccess.model.UserAccess;
import com.ansa.useraccess.model.transfered.User;
import com.ansa.useraccess.model.transfered.UserRole;
import com.ansa.useraccess.repository.UserAccessRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@EnableHystrix
public class UserAccessController {
	
	// autowire the repos
	@Autowired
	UserAccessRepository userAccessRepos;
	
	@Autowired
	UserService userFeignService;
	
	@Autowired
	UserRoleService userRoleFeignService;
	
	private static final String version01 = "v1";
	private static final Logger logger = LoggerFactory.getLogger(UserAccessController.class);
	
	
	/**
	 * 1. When running with API gateway and Eureka Naming Server :
	 *   curl -X GET -H 'Content-Type: application/json' -i http://localhost:9090/cloud-useraccess-service/v1/useraccess
	 */
	@RequestMapping(value=version01+"/useraccess",method=RequestMethod.GET)
	public List<UserAccess> getAllAccessList(){
		logger.info("Get lisst of users configured with Roles Access");
		return userAccessRepos.findAll();
	}
	
	@RequestMapping(value=version01+"/useremail/{email}/role/{rolename}",method=RequestMethod.POST)
	public ResponseEntity<UserAccess> addUserToRoleMapping(@PathVariable("email") String email,
			 @PathVariable("rolename") String rolename) {
		
		logger.debug("Inside addUserToRoleMapping with param values email:["+email+"] , rolename:["+
		rolename +"]");
		
		UserRole userRole= fetchValidRole(rolename);
		User user = fetchValidUser(email);
		
		UserAccess userAccess = new UserAccess(1l,user.getEmailId(),
				user.getFirstName()+" "+user.getLastName(),userRole.getRoleDesc());
		
		userAccessRepos.save(userAccess);
		return ResponseEntity.status(HttpStatus.CREATED).body(userAccess);
	}
	
	@HystrixCommand(fallbackMethod="fallbackFetchValidRole")
	public UserRole fetchValidRole(String roleName) {
		UserRole userRole = null;
		try{
			userRole = userRoleFeignService.getUserRoleByName(roleName);
		}catch(Exception e)
		{ 
			logger.error("[cloud-user-role-service] didnt return a role for the given rolename - "+ roleName);
			throw new RuntimeException("[cloud-user-role-service] didnt return a role for the given rolename");
		}
		return userRole;
	}
	
    /**
     * @param roleName
     * fallback methof to return the default role as guest
     * @return
     */
    public UserRole fallbackFetchValidRole() {
    	logger.warn("Hystrix fallback method called while fetching Role by roleName ,sending back default role");
    	return new UserRole(5, "guest", "guest");
		
	}
    
    
    @HystrixCommand(fallbackMethod="fallbackFetchValidEmail")
	public User fetchValidUser(String email) {
    	User user = null;
		try{
			user = userFeignService.getUserByEmailId(email);
		}catch(Exception e)
		{ 
			logger.error("[cloud-user-service] didnt return a User for the given email - "+ email);
			throw new RuntimeException("[cloud-user-role-service] didnt return a role for the given rolename");
		}
		return user;
	}
    
    public User fallbackFetchValidEmail() {
    	logger.warn("Hystrix fallback method called while fetching User by email sending back default User");
    	return new User(1l,"Satadru","Basu","email@email.com","password");
    }

}
