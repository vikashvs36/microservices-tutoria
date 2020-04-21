package com.microservice.currencyexchangeservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservice.currencyexchangeservice.domain.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	
//	@Query("select ev from ExchangeValue ev where ev.currencyFrom=:currencyFrom and ev.currencyTo=:currencyTo")
	Optional<ExchangeValue> findByCurrencyFromAndCurrencyTo(String currencyFrom, String currencyTo);

}
