package com.practice.microservice.currencyconversionservicev2.feign;


import com.practice.microservice.currencyconversionservicev2.dto.CurrencyConversionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Spring cloud load balancer has already been included together with netflix eureka client.
 * --> when feign perform REST calls to other services, this client load balancer work with Eureka and can evenly distribute calls to registered instances
 * that resolved (matched) with given service name.
 */
@FeignClient(name = "api-gateway")
//@FeignClient(name = "currency-exchange-service") // config this for this service to directly contact currency-exchange-service
public interface CurrencyExchangeServiceFeign {

    @GetMapping("/currency-exchange/from/{from}/to/{to}") // using uri to service
    CurrencyConversionDTO retrieveExchangeValue(@PathVariable String from,
                                                @PathVariable String to);
}
