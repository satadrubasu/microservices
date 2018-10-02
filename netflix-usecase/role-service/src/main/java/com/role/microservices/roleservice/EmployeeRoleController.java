package com.role.microservices.roleservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeRoleController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRoleController.class);
	
	@Autowired
	EmployeeRoleRepository roleRepo;
	
	/*@GetMapping("/role/{roleId}")
	public EmployeeRole getRoleById(@PathVariable Long roleId)
	{
		EmployeeRole roleRecord = roleRepo.findOne(roleId);
		return roleRecord;
	}*/
	
	@GetMapping("/role/{roleName}")
	public EmployeeRole getRoleByName(@PathVariable String roleName)
	{
		logger.info("Inside getRoleByName ");
		EmployeeRole roleRecord = roleRepo.findByRoleName(roleName);
		return roleRecord;
	}
	
	
	
	

}
