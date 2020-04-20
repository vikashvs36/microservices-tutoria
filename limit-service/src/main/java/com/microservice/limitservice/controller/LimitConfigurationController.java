package com.microservice.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.limitservice.config.Configuration;
import com.microservice.limitservice.domain.LimitConfiguration;


@RestController
@RequestMapping(value = "/api/limit-service")
public class LimitConfigurationController {

	@Autowired
	private Configuration configuration;
	
	@GetMapping(value = "/limits")
	public LimitConfiguration fetchLimitConfiguration() {
		return new LimitConfiguration(configuration.getMin(), configuration.getMax());
	}
}
