package com.microservice.limitservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.limitservice.controller.domain.LimitConfiguration;

@RestController
@RequestMapping(value = "/api/limit-service")
public class LimitConfigurationController {

	@GetMapping(value = "/limits")
	public LimitConfiguration fetchLimitConfiguration() {
		return new LimitConfiguration(1,10);
	}
}
