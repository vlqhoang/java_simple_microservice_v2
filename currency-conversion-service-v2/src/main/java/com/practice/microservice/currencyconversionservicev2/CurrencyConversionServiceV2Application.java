package com.practice.microservice.currencyconversionservicev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class CurrencyConversionServiceV2Application {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceV2Application.class, args);
	}

}
