# CURRENCY-CALCULATION-SERVICE

**Currency-calculation-service** is created to calculate the total value of currency conversion if multiple number of currency converted into another currency. Main perpous to create this application is to implement **Feign Client** and **Client side load balancing with Ribbon**. Let's see step by step.

### > Added Dependencies

* Spring Web
* Spring Boot DevTools

### > Application.yml

Mention the appliction name as *currency-calculation-service* and run it on port *4444*

	spring:
	  application:
	    name: currency-calculation-service
	    
	server:
	  port: 4444