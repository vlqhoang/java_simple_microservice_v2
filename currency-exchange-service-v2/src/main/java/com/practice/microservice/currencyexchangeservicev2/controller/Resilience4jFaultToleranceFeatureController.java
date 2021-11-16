package com.practice.microservice.currencyexchangeservicev2.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Resilience4jFaultToleranceFeatureController {

    private final Logger logger = LoggerFactory.getLogger(Resilience4jFaultToleranceFeatureController.class);
    private static final String DUMMY_API_ENDPOINT = "http://localhost:8080/some-dummy-url";
    private static final String DEFAULT_FALLBACK_RESPONSE = "FALLING BACK!.";

    @GetMapping("/sample-api")
    // @Retry(name = "default") // retry with default strategy - attempting 3 connections.
    // @Retry(name = "default-strategy", fallbackMethod = "hardcodedResponse") // retry with configured strategy - attempting 5 connections.
    // if few first calls to downed service are detected -> do fall back immediately
    // For a certain time, the circuit breaker logic will try to check if the calling service is back online (configurable).
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        logger.info("Invoking sample api controller!.");

        // creating dummy call that  will fail.
        final ResponseEntity<String> forEntity = new RestTemplate().getForEntity(DUMMY_API_ENDPOINT, String.class);
		return forEntity.getBody();
    }

    @GetMapping("/sample-api2")
    @RateLimiter(name = "default")
    @Bulkhead(name="default")
    public String sampleApi2() {
        return "Sample API 2!.";
    }

    /**
     * Fall back method
     * @param exception: resilience4j will pass the error to this method on fallback.
     */
    private String hardcodedResponse(Exception exception) {
        logger.error("Error with api, returning fall back message!. Error was {}", exception.toString());
        return DEFAULT_FALLBACK_RESPONSE;
    }
}
