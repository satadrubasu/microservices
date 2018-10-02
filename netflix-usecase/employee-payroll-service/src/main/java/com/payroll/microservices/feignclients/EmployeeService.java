package com.payroll.microservices.feignclients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.payroll.microservices.emppayrollservice.EmployeePayroll;

/**
 * @author satadru.basu
 * Feign Client to replay RestTemplate and act as interface rest client call to
 * Service --> EmployeeService
 * Method Signature is copied to that from the EmployeeServiceController
 * Note : the return type is however mapped to a model available in this EmployeePayrollService
 *   the name is the spring.application.name of the CALLED service
 * 
 * Part 2: instead of directly using FeignClient to call employee-service , call zuul Apig Gateway service
 *   "zuul-edge-server"
 *   Also in the Get Mapping prefix the URL with the service name.
 */
//@FeignClient(name="employee-service",url="localhost:7790")

//without zuul  - @FeignClient(name="employee-service")
@FeignClient(name="zuul-edge-server")
@RibbonClient(name="employee-service")
public interface EmployeeService {
	
	// without zuul - @GetMapping("/employee/{empId}")  without zuul we dont need to prefix the app name.
	@GetMapping("/employee-service/employee/{empId}")  
	public EmployeePayroll getEmployeeDetails(@PathVariable("empId") Long empId);

}
