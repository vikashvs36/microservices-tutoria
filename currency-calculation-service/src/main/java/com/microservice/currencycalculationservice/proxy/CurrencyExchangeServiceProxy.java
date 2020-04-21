package com.microservice.currencycalculationservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.currencycalculationservice.dto.ExchangeValue;

@FeignClient(name = "currency-exchange-service", url = "http://localhost:2222/")
public interface CurrencyExchangeServiceProxy {
	
	@GetMapping(value = "/api/currency-exchange-service/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to);

}