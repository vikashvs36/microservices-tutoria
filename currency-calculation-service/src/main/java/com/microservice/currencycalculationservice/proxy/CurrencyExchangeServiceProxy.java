package com.microservice.currencycalculationservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.currencycalculationservice.dto.ExchangeValue;

//@FeignClient(name = "currency-exchange-service", url = "http://localhost:2222/")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "zuul-api-gatway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
	
	// Before Gatway server
//	@GetMapping(value = "/api/currency-exchange-service/from/{from}/to/{to}")
	// After Gatway server
//	@GetMapping(value = "/currency-exchange-service/api/currency-exchange-service/from/{from}/to/{to}")
	// Before Gatway server with service id and path
	@GetMapping(value = "/api/currency-exchange-service/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to);

}