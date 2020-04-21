# CURRENCY EXCHANGE SERVICE

Currency-exchange-service is a simple microService to implement conversion the value of money from one currency to another currency. We are here using the features of microServices, is **Spring Cloud Config Client** to get the mySQL configuration  from **Spring Cloud Config Server** which is we already implemented in **Spring-Cloud-Config-Server** services. You can visit it from [here](https://github.com/vikashvs36/microservices-tutorial/tree/master/spring-cloud-config-server)

### > Added Dependency

* Spring Web
* Spring Boot DevTools
* Spring Data JPA
* MYSQL
* Spring Cloud Config Client

### > Application.yml

In this project I am using application.yml but you can use either application.yml or application.properties as your comfort.

	spring:
		application:
			name: currency-exchange-service
		datasource:
			url: jdbc:mysql://localhost:3306/currency_exchange_db?createDatabaseIfNotExist=true
			username: root
			password: *****
		jpa:
			hibernate:
			ddl-auto: create
			show-sql: true
	server:
		port: 2222

Mentioned here of datasource and jpa properties in applicaiton.yml. after we get it from Config Server.

### > Enable the config client into this application

To enable the config client feature in this application, please follow the below step :

**1st step : Add Dependency**

	 <dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-config</artifactId>
	</dependency>
	
**2nd step :**

Please add **bootstrap.yml** file to clone the configuration properties before reached on the **aplication.yml**. After create the bootstrap.yml file, Please add **spring.cloud.config.uri** property to fetch from Config Server and add **spring.profiles.active** property to fetch configuration from which profile.

	spring:
	  cloud:
	    config:
	      uri: http://localhost:8888
	      
	  profiles:
	    active: dev
    
**Note :** After add this file and properties, No need to Datasouce and Jpa properties in application.yml file.