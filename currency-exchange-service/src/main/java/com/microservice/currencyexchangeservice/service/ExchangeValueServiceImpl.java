package com.microservice.currencyexchangeservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.currencyexchangeservice.domain.ExchangeValue;
import com.microservice.currencyexchangeservice.repository.ExchangeValueRepository;

@Service
public class ExchangeValueServiceImpl implements ExchangeValueService {
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;
	
	@Override
	public ExchangeValue retriveExchangeValue(String from, String to) {
		Optional<ExchangeValue> findbyFromAndTo = exchangeValueRepository.findByCurrencyFromAndCurrencyTo(from, to);
		return findbyFromAndTo.isPresent() ? findbyFromAndTo.get() : null ;
	}

}
