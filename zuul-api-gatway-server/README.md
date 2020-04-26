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

### Setting up Zuul API Gateway

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
	
So. After implemented this filter we are able to get the logging of any services from ZullLoggingFilter.  

I am going to start *eureka-naming-server, zull-api-gateway-server, currency-exchange-service and currency-calculation-service* services.

There is a convention to get the any service request through *zull-api-gateway-server* is given below : 
 
	http://localhost:{gateway-server-port}/{application-name}/{URI}
 	
Port belongs to *zull-api-gateway-server*, application name and port are service name and server port accordingly which would be mentioned on application.yml of corresponding services. For example, After start these service, I want to access the *currency-exchange-service* through *zull-api-gateway-server*. So how can we access it. Let's see : 

	// We used to run separately of currency-exchange-service
	http://localhost:2222/api/currency-exchange-service/from/USD/to/INR
	
	// Intercepting through zull-api-gateway-server
	http://localhost:8080/currency-exchange-service/api/currency-exchange-service/from/USD/to/INR
	
