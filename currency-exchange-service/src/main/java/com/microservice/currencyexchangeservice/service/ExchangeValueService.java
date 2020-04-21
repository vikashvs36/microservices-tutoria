package com.microservice.currencyexchangeservice.service;

import com.microservice.currencyexchangeservice.domain.ExchangeValue;

public interface ExchangeValueService {

	ExchangeValue retriveExchangeValue(String from, String to);

}
