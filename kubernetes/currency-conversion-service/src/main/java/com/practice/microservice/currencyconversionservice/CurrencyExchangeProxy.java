package com.practice.microservice.currencyconversionservice;

import com.practice.microservice.currencyconversionservice.dto.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//CHANGE-KUBERNETES
// default - when a service get expose with name 'currency-exchange', an environment variable 'CURRENCY_EXCHANGE_SERVICE_HOST' is made available to pod (version 0.1.0-SNAPSHOT)
//@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_SERVICE_HOST:http://localhost}:8000")
// Centralized configuration using config map (version 0.1.1-SNAPSHOT)
@FeignClient(name = "currency-exchange", url = "${CURRENCY_EXCHANGE_URI:http://localhost}:8000")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);

}
