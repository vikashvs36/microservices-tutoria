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

