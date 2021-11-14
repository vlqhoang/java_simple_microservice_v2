package com.practice.microservice.currencyexchangeservicev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyExchangeServiceV2Application {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeServiceV2Application.class, args);
	}

}
