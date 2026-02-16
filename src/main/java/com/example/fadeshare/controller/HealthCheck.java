package com.example.fadeshare.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/health")
    private String healthcheck(){
        return "OK";
    }
}
