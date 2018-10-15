package com.ansa.useraccess.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ansa.useraccess.model.transfered.UserRole;

//Without API gateway and loadbalancer behind Eureka
//@FeignClient(name="cloud-user-role-service",url="localhost:7090")

@FeignClient(name="zuul-proxy-server") //{API Gateway service name} 
@RibbonClient(name="cloud-user-role-service") // {Service-Name}
public interface UserRoleService {
	
	static final String version01 = "v1";

	// Without Zuul API gateway the 'cloud-user-role-service' app name doesnt need to be prefixed
	@RequestMapping(value="/cloud-user-role-service/v1/userroles/{rolename}",
			method=RequestMethod.GET)
	public UserRole getUserRoleByName(@PathVariable("rolename") String roleName);
}
