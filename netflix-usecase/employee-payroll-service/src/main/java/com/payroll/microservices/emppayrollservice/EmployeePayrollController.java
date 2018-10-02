package com.payroll.microservices.emppayrollservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.payroll.microservices.feignclients.EmployeeService;
import com.payroll.microservices.feignclients.RoleService;

@RestController
public class EmployeePayrollController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeePayrollController.class);

	@Autowired
	EmployeePayrollRepository empPayrollRepos;
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	RoleService roleService;
	
	
	//test URL POST to -- http://localhost:7792/employee/1000/role/Admin
	@PostMapping("/employee/{empId}/role/{roleName}")
	public EmployeePayroll insertEmployeePayrollDetails(@PathVariable Long empId,@PathVariable String roleName)
	{
		
		logger.info("Inside insertEmployeePayrollDetails");
		
		EmployeePayroll employeePayroll = new EmployeePayroll();
		
		/*
		//Call Employee Service based of the empId and load 1st part of details for EmployeePayrollEntity
		ResponseEntity<EmployeePayroll> forEntity = new RestTemplate().getForEntity("http://localhost:7790/employee/{empId}",EmployeePayroll.class,empId);
		employeePayroll =forEntity.getBody();
		
		//Call Role Service based of the RoleName and load 2nd part of details for EmployeePayrollEntity ( derived from Role Entity )
		
		ResponseEntity<EmployeePayroll> forRoleEntity = new RestTemplate().getForEntity("http://localhost:7791/role/{roleName}", EmployeePayroll.class,roleName);
		employeePayroll.setRoleId(forRoleEntity.getBody().getRoleId());
		employeePayroll.setRoleName(roleName);
		employeePayroll.setRoleDescription(forRoleEntity.getBody().getRoleDescription());
		
		Alternate way of calling rest using feign client - refer interface EmployeeService
		*/

		//feign client call to EmployeeService
		employeePayroll=employeeService.getEmployeeDetails(empId);
		logger.info("## employeeService called and returned FirstName:" + employeePayroll.getFirstName());
		
		
		//feign client call to RoleService
		EmployeePayroll roleDetails = roleService.getRoleByName(roleName);
		logger.info("## employeeService called and returned Role Description:" + roleDetails.getRoleDescription());
		
		employeePayroll.setRoleId(roleDetails.getRoleId());
		employeePayroll.setRoleName(roleDetails.getRoleName());
		employeePayroll.setRoleDescription(roleDetails.getRoleDescription());
		
		logger.info("### saving employeePayroll/Updating...");
		empPayrollRepos.save(employeePayroll);
		
		return employeePayroll;
		
	}
}
