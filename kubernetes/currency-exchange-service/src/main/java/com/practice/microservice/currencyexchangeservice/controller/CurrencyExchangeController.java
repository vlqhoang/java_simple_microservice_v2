package com.practice.microservice.currencyexchangeservice.controller;

import com.practice.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;
import com.practice.microservice.currencyexchangeservice.model.CurrencyExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	private final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		logger.info("retrieveExchangeValue called with {} to {}", from, to);
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		
		if(currencyExchange ==null) {
			throw new RuntimeException("Unable to Find data for " + from + " to " + to);
		}
		
		final String port = environment.getProperty("local.server.port");
		
		//CHANGE-KUBERNETES
		// host name is automatically made available from environment variable - pod name
		final String host = environment.getProperty("HOSTNAME");
		final String version = "v0.1.0";
		
		currencyExchange.setEnvironment(port + " " + version + " " + host);
		return currencyExchange;
		
	}

}
