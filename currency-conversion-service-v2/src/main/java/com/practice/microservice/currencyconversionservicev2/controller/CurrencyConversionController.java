package com.practice.microservice.currencyconversionservicev2.controller;

import com.practice.microservice.currencyconversionservicev2.dto.CurrencyConversionDTO;
import com.practice.microservice.currencyconversionservicev2.feign.CurrencyExchangeServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeServiceFeign currencyExchangeFeign;

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionDTO convertCurrency(@PathVariable String from,
                                                 @PathVariable String to,
                                                 @PathVariable BigDecimal quantity) {

        CurrencyConversionDTO response = currencyExchangeFeign.retrieveExchangeValue(from, to);

        if (response == null) {
            throw new RuntimeException("Exchange ratio not found for " + from + " to " + to);
        }

        response.setQuantity(quantity);
        response.setTotalCalculatedAmount(quantity.multiply(response.getConversionMultiple()));
        return response;
    }
}
