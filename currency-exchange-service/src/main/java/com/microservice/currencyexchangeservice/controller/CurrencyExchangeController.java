package com.microservice.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.currencyexchangeservice.domain.ExchangeValue;
import com.microservice.currencyexchangeservice.service.ExchangeValueService;

@RestController
//@RequestMapping(value = "/api/currency-exchange-service")
public class CurrencyExchangeController {
	
	@Autowired
	private Environment environment; 
	
	@Autowired
	private ExchangeValueService exchangeValueService;
	
	@GetMapping(value = "/from/{from}/to/{to}")
	public ExchangeValue fetchCurrencyExchange(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = exchangeValueService.retriveExchangeValue(from, to);
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchangeValue;
	}
	

}
