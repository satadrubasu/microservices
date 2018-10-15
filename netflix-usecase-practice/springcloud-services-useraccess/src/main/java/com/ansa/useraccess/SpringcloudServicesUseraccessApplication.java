package com.ansa.useraccess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.ansa.useraccess.feignclient") // pkg to scan
public class SpringcloudServicesUseraccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServicesUseraccessApplication.class, args);
	}
}
