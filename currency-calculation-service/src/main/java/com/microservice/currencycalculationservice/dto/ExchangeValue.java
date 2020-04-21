package com.microservice.currencycalculationservice.dto;

public class ExchangeValue {

	private Integer id;
	private String from;
	private String to;
	private Double conversionMultiple;
	private Integer port;
	
	public ExchangeValue() { }

	public ExchangeValue(int id, String from, String to, Double conversionMultiple) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Double getConversionMultiple() {
		return conversionMultiple;
	}

	@Override
	public String toString() {
		return "ExchangeValue [id=" + id + ", from=" + from + ", to=" + to + ", conversionMultiple="
				+ conversionMultiple + ", port=" + port + "]";
	}

	public void setConversionMultiple(Double conversionMultiple) {
		this.conversionMultiple = conversionMultiple;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
