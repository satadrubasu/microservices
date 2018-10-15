package com.ansa.useraccess.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ansa.useraccess.model.transfered.User;

//Without API gateway and loadbalancer behind Eureka
//@FeignClient(name="cloud-user-service",url="localhost:7080")

@FeignClient(name="zuul-proxy-server") //{API Gateway service name} 
@RibbonClient(name="cloud-user-service") // {Service-Name}
public interface UserService {
	
	static final String version01 = "v1";

	// Without Zuul API gateway the 'cloud-user-service' app name doesnt need to be prefixed
	@RequestMapping(value="/cloud-user-service/v1/user/email/{email:.+}/acceptable",
			method=RequestMethod.GET)
	public User getUserByEmailId(@PathVariable("email") String email);
}
