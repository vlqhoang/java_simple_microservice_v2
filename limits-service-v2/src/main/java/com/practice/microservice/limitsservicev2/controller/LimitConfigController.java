package com.practice.microservice.limitsservicev2.controller;

import com.practice.microservice.limitsservicev2.configuration.LimitServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigController {

    @Autowired
    private LimitServiceConfig limits;

    @GetMapping("/limits")
    public LimitServiceConfig retrieveLimits() {
        return limits;
    }
}
