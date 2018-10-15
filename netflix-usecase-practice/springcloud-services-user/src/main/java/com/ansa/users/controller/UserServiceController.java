package com.ansa.users.controller;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ansa.users.jpa.repository.UserRepository;
import com.ansa.users.model.core.User;


/**
 * @author Satadru Basu
 *  Ensure versioning principle of microservices.So all paths to have a version for starter
 */
@RestController
public class UserServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);
	private static final String version01 = "/v1"; // prefix to mapping context
	
	@Autowired
	UserRepository userRepos;
	
	
	/**
	 * 1. When running without eureka Or Gateway
	 *    curl -X GET -H 'Content-Type: application/json' -i http://localhost:7080/v1/users
	 *    
	 * 2. When running with Eureka and API Gateway(9090)
	 *   curl -X GET -H 'Content-Type: application/json' -i http://localhost:9090/cloud-user-service/v1/users
	 */
	@RequestMapping(value=version01+"/users" , method = RequestMethod.GET)
	public List<User> getUsers()
	{
		logger.debug("GET called on API - "+ version01+"/users");
		return userRepos.findAll();
	}
	
	/**
	 * 1. When running without eureka Or Gateway
	 *    curl -X GET -H 'Content-Type: application/json' -i http://localhost:7080/v1/user/1
	 *    
	 * 2. When running with Eureka and API Gateway(9090)
	 *    curl -X GET -H 'Content-Type: application/json' -i http://localhost:9090/cloud-user-service/v1/user/1
	 */
	@RequestMapping(value=version01+"/user/{userId}" , method = RequestMethod.GET)
	public User getUserById(@PathVariable Long userId)
	{
		logger.debug("GET called on API - "+ version01+"/user/"+userId);
		User userRecord = userRepos.findOne(userId);
		return userRecord;
	}
	
	/**
	 * 1. When running without eureka Or Gateway
	 *    curl -X GET -H 'Content-Type: application/json' -i 'http://localhost:7080/v1/user/email/bmail@email.com/acceptable'
	 *    
	 * 2. When running with Eureka and API Gateway(9090)
	 *   curl -X GET -H 'Content-Type: application/json' -i 'http://localhost:9090/cloud-user-service/v1/user/email/bmail@email.com/acceptable'
	 * 
	 */
	@RequestMapping(value=version01+"/user/email/{email:.+}/acceptable" , method = RequestMethod.GET)
	public User getUserByEmailId(@PathVariable("email") String email)
	{
		List<User> matchingUserRecords = new LinkedList<User>();
		logger.debug("GET called on API - "+ version01+"/user/email/"+email+"/acceptable");
		 matchingUserRecords = userRepos.findByEmail(email);
		 return matchingUserRecords.get(0);  // bad coding but just to proceed with testing concepts for now
		
	}
	
	/**
	 * @param user
	 * 1. When running without eureka Or Gateway
	 *    curl -X POST -H 'Content-Type: application/json' -i http://localhost:7080/v1/users --data '{"firstName":"Zac","lastName":"Pardesi","emailId":"zmail@email.com","password":"password"}'
	 *    
	 * 2.When running with Eureka and API Gateway(9090)
	 *   curl -X POST -H 'Content-Type: application/json' -i http://localhost:9090/cloud-user-service/v1/users --data '{"firstName":"Zac","lastName":"Pardesi","emailId":"zmail@email.com","password":"password"}'   
	 * @return
	 */
	@RequestMapping(value=version01+"/users" , method = RequestMethod.POST)
	public ResponseEntity<?> addUser(@RequestBody User user)
	{
		User recordCreated = null;
		try
		{
			recordCreated = userRepos.save(user);
		}catch(Exception e)
		{
			logger.error("Failed creating user ");
		}
		
		if(recordCreated==null)
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		else
			return  ResponseEntity.status(HttpStatus.CREATED).body(recordCreated);
	}

}
