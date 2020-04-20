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

To get the configuration properties from properties file we can mention it into **application.yml :**

	limit-service:
		min: 10
		max: 100
	
### > Way to get the values from properties files into controller using class.

Creating a class named Configuration to get the values from properties files. 

**@Component : ** @Component is the most generic Spring annotation. A class which is decorated with @Component is found during classpath scanning and registered in the context as a Spring bean.

**@ConfigurationProperties(prefix = "limit-service") :** Spring Boot @ConfigurationProperties is letting developer maps the entire .properties and yml file into an object easily. It supports both .properties and .yml file. prefix limit-service, find limit-service.* values.

**Example : ** 

	@Component
	// prefix limit-service, find limit-service.* values in applicaiton.yml file
	@ConfigurationProperties(prefix = "limit-service") 
	public class Configuration {
	
		// Remain properties key will be same which is mention in the application.yml file
		private int min;
		private int max;
		
		// Setter, Getter and Constructor here.
	}

We can use is on the controller to get the values from propertes file as given below : 

	@Autowired
	private Configuration configuration;
	
	@GetMapping(value = "/limits")
	public LimitConfiguration fetchLimitConfiguration() {
		return new LimitConfiguration(configuration.getMin(), configuration.getMax());
	}
	
**It's time to upgrade ourself**

Please pay the more attention here. As of now, In this simple **limit-service** application, We are able to get the configuration values from YMl file. But it's time to think big, There may be different and multiple configuration files. because We works on multiple profile like DEV, QA, PROD and all. So we need to create multiple yml file according to profiles. and suppose we have multiple service so we need to create all configuration profile on every services. So my concern is, It very difficulty to manage all the services configuration files. 

Suppose we have a scenario that after complete the task we need to create a build on QA profile of every service so we will need to change the profile from dev to QA then what we have to do. We will have to change the active profile in all services one by one, who is very difficult for every developer. So we need to centralize it into one service called config server So every services will call from the centralize config server.

There is two term to call the services which is provide by **Spring Cloud**:

* Spring Cloud Config Server
* Spring Cloud Config Client

**Spring Cloud Config Server :** 

We need to focus to on create **Config server** and How to store multiple profiles of different service wise. We left limit-service here for some moment and focus on Config Server. MicroService means, Modules are divided into several service. So Let's go on little interesting tour to understand config server on another application or service : [Spring-Cloud-Config-Server](https://github.com/vikashvs36/microservices-tutoria/tree/master/spring-cloud-config-server) 

  
