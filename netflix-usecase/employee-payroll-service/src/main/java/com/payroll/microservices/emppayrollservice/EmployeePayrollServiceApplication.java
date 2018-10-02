package com.payroll.microservices.emppayrollservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients("com.payroll.microservices.feignclients")
@EnableDiscoveryClient
public class EmployeePayrollServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePayrollServiceApplication.class, args);
	}
	@Bean
    public AlwaysSampler alwaysSampler()  {
       return new AlwaysSampler();
     }
}
