package com.microservice.currencycalculationservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservice.currencycalculationservice.domain.CurrencyExchangeValue;
import com.microservice.currencycalculationservice.dto.ExchangeValue;
import com.microservice.currencycalculationservice.proxy.CurrencyExchangeServiceProxy;

@RestController
//@RequestMapping(value = "/api/currency-calculation-service")
public class CurrencyConvertionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConvertionController.class);
	
	@Autowired
	private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
	
	@GetMapping(value = "/from/{from}/to/{to}/quality/{quantity}")
	public CurrencyExchangeValue fetchCurrencyConvertion(@PathVariable String from, @PathVariable String to,
			@PathVariable Double quantity) {
		
		LOGGER.info("com.microservice.currencycalculationservice.controller.CurrencyConvertionController.fetchCurrencyConvertion(String, String, Double)");

		// Using ResTemplate
		Map<String, String> responseType = new HashMap<>();
		responseType.put("from", from);
		responseType.put("to", to);

		// before Gate-server
//		String url = "http://localhost:2222/api/currency-exchange-service/from/{from}/to/{to}";
		// After Gate-server
		String url = "http://localhost:8080/api/currency-exchange-service/from/{from}/to/{to}";
		ResponseEntity<ExchangeValue> forEntity = new RestTemplate().getForEntity(url, ExchangeValue.class,
				responseType);
		ExchangeValue obj = forEntity.getBody(); 
		
		return new CurrencyExchangeValue(obj.getId(), from, to,
				obj.getConversionMultiple(), quantity, obj.getConversionMultiple() * quantity, obj.getPort());
	}
	
	@GetMapping("/feign/from/{from}/to/{to}/quality/{quantity}")
	public CurrencyExchangeValue fetchCurrencyConvertionByFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable Double quantity) {
		
		LOGGER.info("com.microservice.currencycalculationservice.controller.CurrencyConvertionController.fetchCurrencyConvertionByFeign(String, String, Double)");

		// Using Feign Client
		ExchangeValue obj = currencyExchangeServiceProxy.retriveExchangeValue(from, to); 
		
		return new CurrencyExchangeValue(obj.getId(), from, to,
				obj.getConversionMultiple(), quantity, obj.getConversionMultiple() * quantity, obj.getPort());
	}
	
}