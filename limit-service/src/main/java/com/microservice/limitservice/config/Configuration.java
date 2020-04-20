package com.microservice.limitservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "limit-service") // prefix is key and it is same as mention in applicaiton.yml file
public class Configuration {

	// Remain properties key will be same which is mention in the application.yml file
	private int min;
	private int max;
	
	public Configuration() { }

	public Configuration(int min, int max) {
		this.min = min;
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}
}
