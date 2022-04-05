package com.santander.banco811.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @GetMapping("/health-check")
  public String healthCheck() {
    return "Service is up and running!";
  }
}
