# CURRENCY-CALCULATION-SERVICE

**Currency-calculation-service** is created to calculate the total value of currency conversion if multiple number of currency converted into another currency. Main purpose to create this application is to implement **Feign Client** and **Client side load balancing with Ribbon**. Let's see step by step.

### Added Dependencies

* Spring Web
* Spring Boot DevTools

### Application.yml

Mention the application name as *currency-calculation-service* and run it on port *4444*

	spring:
	  application:
	    name: currency-calculation-service
	    
	server:
	  port: 4444
	  
### Creating an API 

Created a CurrencyConverion Controller to use RestTemplate and Feign client. implemented a api to fetch the value of multiple currency at the time of exchange it. Api is given below : 

	http://localhost:4444/api/currency-calculation-service/from/USD/to/INR/quality/10
 
There is already created [currency-exchange-service](https://github.com/vikashvs36/microservices-tutorial/tree/master/currency-exchange-service) to fetch the conversion value of 1 currency as compare to two currency. So We have A API *http://localhost:2222/api/currency-exchange-service/from/USD/to/INR* to apply the same. But we have a problem and that is, This given API is build on another service so main problem is call to API from one service to another. In such case, Two way to handle this issue which is given below, we will see it one by one :

* RestTemplate 
* Feign Client
    
### RestTemplate client

RestTemplate is way to call an API of another services. RestTemplate is present in *org.springframework.web.client.RestTemplate*. this way use in most of the spring or spring-boot application. We can use it for all method type like *Get, Post, Put, Delete* and so on.

	Map<String, String> responseType = new HashMap<>();
	responseType.put("from", from);
	responseType.put("to", to);

	String url = "http://localhost:2222/api/currency-exchange-service/from/{from}/to/{to}";
	ResponseEntity<ExchangeValue> forEntity = new RestTemplate().getForEntity(url, ExchangeValue.class, responseType);
	ExchangeValue obj = forEntity.getBody();
	
**ExchangeValue** is a response dto to contain Object which response is get from their API.

This way, we can call API's but this approach takes more time and affords of developer to implement this and using this way URL needs to do hard coded. So overcome this issue we need to implement the feign Client.


