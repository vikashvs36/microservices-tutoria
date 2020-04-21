package com.microservice.currencycalculationservice.domain;

public class CurrencyExchangeValue {

	private Integer id;
	private String currency_from;
	private String currency_to;
	private Double conversionMultiple;
	private Double quantity;
	private Double totalCalculatedAmmount;
	private Integer port;

	public CurrencyExchangeValue() {}

	public CurrencyExchangeValue(Integer id, String currency_from, String currency_to, Double conversionMultiple,
			Double quantity, Double totalCalculatedAmmount, Integer port) {
		this.id = id;
		this.currency_from = currency_from;
		this.currency_to = currency_to;
		this.conversionMultiple = conversionMultiple;
		this.quantity = quantity;
		this.totalCalculatedAmmount = totalCalculatedAmmount;
		this.port = port;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrency_from() {
		return currency_from;
	}

	public void setCurrency_from(String currency_from) {
		this.currency_from = currency_from;
	}

	public String getCurrency_to() {
		return currency_to;
	}

	public void setCurrency_to(String currency_to) {
		this.currency_to = currency_to;
	}

	public Double getConversionMultiple() {
		return conversionMultiple;
	}

	public void setConversionMultiple(Double conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getTotalCalculatedAmmount() {
		return totalCalculatedAmmount;
	}

	public void setTotalCalculatedAmmount(Double totalCalculatedAmmount) {
		this.totalCalculatedAmmount = totalCalculatedAmmount;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
