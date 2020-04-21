package com.microservice.currencyexchangeservice.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchange_value")
public class ExchangeValue {

	@Id
	private Long id;
	@Column(name = "currency_to")
	private String currencyTo;
	@Column(name = "currency_from")
	private String currencyFrom;
	@Column(name = "conversion_multiple")
	private BigDecimal conversionMultiple;
	private Integer port;
	
	public ExchangeValue() {}

	public ExchangeValue(Long id, String currencyTo, String currencyFrom, BigDecimal conversionMultiple) {
		super();
		this.id = id;
		this.currencyTo = currencyTo;
		this.currencyFrom = currencyFrom;
		this.conversionMultiple = conversionMultiple;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(BigDecimal conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
