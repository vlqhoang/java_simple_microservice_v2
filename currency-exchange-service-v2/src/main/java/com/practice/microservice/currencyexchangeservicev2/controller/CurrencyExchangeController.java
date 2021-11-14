package com.practice.microservice.currencyexchangeservicev2.controller;

import com.practice.microservice.currencyexchangeservicev2.model.ExchangeValue;
import com.practice.microservice.currencyexchangeservicev2.repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(final @PathVariable String from,
                                               final @PathVariable String to) {

        ExchangeValue exchangeValue = exchangeValueRepository.findByCurrencies(from, to);

        if (exchangeValue == null) {
            throw new RuntimeException("No exchange data found for " +  from + " to " + to);
        }

        exchangeValue.setEnvironment(environment.getProperty("local.server.port"));
        return exchangeValue;
    }
}
