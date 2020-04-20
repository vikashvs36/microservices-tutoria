# LIMIT-SERVICE

Limit-Service is creating a simple microservice which is used for **Spring Cloud Config Server**. First we get the configuration from properties file which is from same service. After that the configuration properties will be get from spring cloud config server and will be see to get multiple profile as well.

## Setup Limit-Service

### > **Added Dependency**

* Spring Web
* Spring Boot DevTools

**Note :** You have to add the **Config Client** dependency at the time of create the service.

### > Application.yml

In this project I am using application.yml but you can use either application.yml or application.properties as your comfort.

You have to define application name as limit-service and set the port as your choice.  

	spring:
		application:
			name: limit-service
	server:
		port: 1111
		
### > Add LimitConfiguration Controller 

Adding LimitConfiguration Controller to create a API to get the limit configuration values like min and max value.

	@RestController
	@RequestMapping(value = "/api/limit-service")
	public class LimitConfigurationController {
	
		@GetMapping(value = "/limits")
		public LimitConfiguration fetchLimitConfiguration() {
			return new LimitConfiguration(1,10);
		}
	}  

After run the limit-service application the result will be got from API : **http://localhost:1111/api/limit-service/limits**. It will give the static result. 


	