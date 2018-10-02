package com.payroll.microservices.employeeservice;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@EnableHystrix
@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeRepository empRepos;

	@Autowired
	Environment environment;
	
	@Autowired
	EmployeeConfiguration empConfiguration;

	@GetMapping("/employee/{empId}")
	public Employee getEmployeeDetails(@PathVariable Long empId) {

		logger.info("Inside getEmployeeDetails");
		Employee emp = empRepos.findOne(empId);
		// Optional<Employee> emp = empRepos.findById(empId);
		emp.setPort(environment.getProperty("local.server.port"));
		return emp;
	}

	@HystrixCommand(fallbackMethod = "fallBackEmployeeDetails")
	@GetMapping("/employee/fault-tolerance")
	public Employee getDetails() {
		
		//force encountering a runtime exception to demonstrate 
		//navigation to fallback method
		throw new RuntimeException("Check Hysterix behaviour");
	}
	
	public Employee fallBackEmployeeDetails()
	{
		return new Employee(613316L, empConfiguration.getDefaultFirstName(), 
				empConfiguration.getDefaultLastName(), new Date());
	}
	
	
}
