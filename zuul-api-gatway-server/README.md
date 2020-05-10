# ZUUL-API-GATWAY-SERVER

Zuul Server is an API Gateway application. It handles all the requests and performs the dynamic routing of microservice applications. It works as a front door for all the requests.

It is important to remember that maintaining an API-Gateway service brings you more benefits other than load balancing like below.

* Authentication, Authorization and Security
* Monitoring
* Dynamic Routing
* Static Response handling
* Rate limiting

**Added Dependency**

* Zuul, 
* Eureka Discovery, 
* DevTools.

**application.yml**

	spring:
	  application:
	    name: zuul-api-gatway-server
	    
	server:
	  port: 8080

## Setting up Zuul API Gateway

**Step 1 : Add dependency**

Added dependency which is Zuul and Eureka Discovery 

	<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-zuul</artifactId>
		</dependency>
	<dependency>
		<groupId>org.springframework.cloud</groupId>
		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
	</dependency>
	
**Step 2 : Add Annotation**

Added Annotation *@EnableZuulProxy and @EnableDiscoveryClient* into main class.

	@SpringBootApplication
	@EnableDiscoveryClient
	@EnableZuulProxy
	public class ZuulApiGatwayServerApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(ZuulApiGatwayServerApplication.class, args);
		}
	}
	
**Step 3 : application.yml**

Added *eureka.client.service-url.defaultZone* to register for Eureka Server.

	eureka:
	  client:
	    service-url:
	      defaultZone: http://localhost:8761/eureka/

### Zuul Components

Zuul has mainly four types of filters that enable us to intercept the traffic in different timeline of the request processing for any particular transaction. We can add any number of filters for a particular url pattern.

* pre filters – are invoked before the request is routed.
* post filters – are invoked after the request has been routed.
* route filters – are used to route the request.
* error filters – are invoked when an error occurs while handling the request.


## Filter and Router : Zuul

### Implementing Zuul Logging Filter

Now I am going to add a *Zuul filter* to apply logging in all services. As you know every service needs to intercept from gateway service where we can implement some common functionality. So I want to logging every request which is requested from any service.   

It's a simple class which is annotated with @Component to create the bean by spring and extend **ZuulFilter** abstract class. All abstract method of the ZuulFilter class needs to be implemented as given below : 

	@Component
	public class ZuulLoggingFilter extends ZuulFilter {
		private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
		
		@Override
		public boolean shouldFilter() {
			return true;
		}
	
		@Override
		public Object run() throws ZuulException {
			HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
			LOGGER.info("Request from URI : {}", request.getRequestURI());
			return null;
		}
	
		@Override
		public String filterType() {
			return "pre";
		}
	
		@Override
		public int filterOrder() {
			return 1;
		}
	}

In the run() method you can add your logic according to your requirement.    

With the help of RequestContext we can get the URL of any services. It needs to be import from in com.netflix.zuul.context.RequestContext package.

	HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
	String URL = request.getRequestURI(); 
	
So. After implemented this filter we are able to get the logging of any services from ZuulLoggingFilter.  

I am going to start *eureka-naming-server, zuul-api-gateway-server, currency-exchange-service and currency-calculation-service* services.

There is a convention to get the any service request through *zuul-api-gateway-server* is given below : 
 
	http://localhost:{gateway-server-port}/{application-name}/{URI}
 	
Port belongs to *zuul-api-gateway-server*, application name and port are service name and server port accordingly which would be mentioned on application.yml of corresponding services. For example, After start these service, I want to access the *currency-exchange-service* through *zuul-api-gateway-server*. So how can we access it. Let's see : 

	// We used to run separately of currency-exchange-service
	http://localhost:2222/api/currency-exchange-service/from/USD/to/INR
	
	// Intercepting through zuul-api-gateway-server
	http://localhost:8080/currency-exchange-service/api/currency-exchange-service/from/USD/to/INR
	
### Logging through zuul-api-gateway-server interact with Service to Service using feign Client

Currency-calculation-service is interacting with currency-exchange-service. As of now, you access the currency-calculation-service it will call through the gateway service but there has one more call from currency-exchange-service which is direct call through feign client. there is a problem every service need to call through Gateway api. so let's see how to feign client interact with gateway service.

Name attribute of annotation in Proxy class needs to change in Currency-calculation-service to interact with zuul-api-gateway-server : 
	
	// Convert from 
	@FeignClient(name = "currency-exchange-service")
	
	// Convert to
	@FeignClient(name = "zuul-api-gatway-server")
	
and as above convention to interact with the any url though zuul-api-gateway-server, Request needs to change as given below :

	// Convert from 
	@GetMapping(value = "/api/currency-exchange-service/from/{from}/to/{to}")
	
	// Convert to
	@GetMapping(value = "/currency-exchange-service/api/currency-exchange-service/from/{from}/to/{to}")
	
After these changes are done in CurrencyExchangeServiceProxy from Currency-calculation-service look like as given below : 

	@FeignClient(name = "zuul-api-gatway-server")
	@RibbonClient(name = "currency-exchange-service")
	public interface CurrencyExchangeServiceProxy {
		
		@GetMapping(value = "/currency-exchange-service/api/currency-exchange-service/from/{from}/to/{to}")
		public ExchangeValue retriveExchangeValue(@PathVariable String from, @PathVariable String to);
	}
	
Output from Zuul-api-getway-server : 

	2020-04-26 17:22:38.559  INFO 1072 --- [nio-8080-exec-3] c.m.z.filter.ZuulLoggingFilter           : Request from URI : /currency-calculation-service/api/currency-calculation-service/feign/from/USD/to/INR/quality/100
	2020-04-26 17:22:38.580  INFO 1072 --- [nio-8080-exec-2] c.m.z.filter.ZuulLoggingFilter           : Request from URI : /currency-exchange-service/api/currency-exchange-service/from/USD/to/INR
	
	
## Router : Zuul

Routing is an integral part of a microservice architecture. For example, / may be mapped to your web application, /api/users is mapped to the user service and /api/shop is mapped to the shop service. 
Zuul is a JVM-based router and server-side load balancer from Netflix.

	zuul:
  		ignoredServices: '*'
  		routes:
    		exchange: /myExchange/**
    		
In the preceding example, all services are ignored, except for exchange.

	zuul:
  		routes:
    		exchange: /myExchange/**
    		
The preceding example means that HTTP calls to /myExchange get forwarded to the exchange service (for example /myExchange/101 is forwarded to /101).

To get more fine-grained control over a route, you can specify the path and the serviceId independently, as follows:

	zuul:
  		routes:
    		exchange_service:
      			path: /myExchange/**
      			serviceId: exchange_service

The preceding example means that HTTP calls to /myExchange get forwarded to the exchange_service service. The route must have a path 
that can be specified as an ant-style pattern, so /exchange_service/* only matches one level, but /exchange_service/** matches hierarchically.

The location of the back end can be specified as either a serviceId (for a service from discovery) or a url (for a physical location), as shown in the following example:

	zuul:
  		routes:
    		exchange_service:
      			path: /myExchange/**
    			url: https://localhost:2222/exchange_service
    			
These simple url-routes do not get executed as a HystrixCommand, nor do they load-balance multiple URLs with Ribbon. 

As we mentioned one service route, can be configure multiple service routes.

	zuul:
  		routes:
    		CURRENCY-EXCHANGE-SERVICE:
      			path: /currency-exchange-service/**
    			serviceId: CURRENCY-EXCHANGE-SERVICE
    		CURRENCY-CALCULATION-SERVICE:
    			path: /currency-calculation-service/**
    			serviceId: CURRENCY-CALCULATION-SERVICE
    			
We can specify common prefix for all services as well.

	zuul:
  		prefix: /api 
  		routes:
    		CURRENCY-EXCHANGE-SERVICE:
      			path: /currency-exchange-service/**
    			serviceId: CURRENCY-EXCHANGE-SERVICE
    		CURRENCY-CALCULATION-SERVICE:
    			path: /currency-calculation-service/**
    			serviceId: CURRENCY-CALCULATION-SERVICE
    			
After mention this routes services URL can be called as prefix and path like this:

	// CURRENCY-EXCHANGE-SERVICE
	http://localhost:8080/api/currency-exchange-service/**
	
	// CURRENCY-CALCULATION-SERVICE:
	http://localhost:8080/api/currency-calculation-service/**
	
let's see after Implemented Zuul-api-gatway-server, specific service URL will be converted into Gatway-server like :

	// CURRENCY-EXCHANGE-SERVICE
	http://localhost:2222/from/USD/to/INR
	// GatWay-Service
	http://localhost:8080/api/currency-exchange-service/from/USD/to/INR
	
	// CURRENCY-EXCHANGE-SERVICE
	http://localhost:4444/from/USD/to/INR/quality/100
	// GatWay-Service
	http://localhost:8080/api/currency-calculation-service/from/USD/to/INR/quality/100
	
	// CURRENCY-EXCHANGE-SERVICE - with feign 
	http://localhost:4444/feign/from/USD/to/INR/quality/100
	// GatWay-Service - with feign
	http://localhost:8080/api/currency-calculation-service/feign/from/USD/to/INR/quality/100
	