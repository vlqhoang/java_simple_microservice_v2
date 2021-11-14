package com.practice.microservice.limitsservicev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LimitsServiceV2Application {

	public static void main(String[] args) {
		SpringApplication.run(LimitsServiceV2Application.class, args);
	}

}
