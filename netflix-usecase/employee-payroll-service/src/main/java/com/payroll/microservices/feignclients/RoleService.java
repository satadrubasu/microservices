package com.payroll.microservices.feignclients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.payroll.microservices.emppayrollservice.EmployeePayroll;

//@FeignClient(name="role-service",url="localhost:7791")
@RibbonClient(name="role-service")
//@FeignClient(name="role-service") removed as now going via zuul gateway
@FeignClient(name="zuul-edge-server")
public interface RoleService {
	
	//@GetMapping("/role/{roleName}")
	@GetMapping("/role-service/role/{roleName}")
	public EmployeePayroll getRoleByName(@PathVariable("roleName") String roleName);

}
