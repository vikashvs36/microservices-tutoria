# SPRING-CLOUD-CONFIG-SERVER

**Spring-Cloud-Config-Server** is creating as config server to store configuration of all config client services like **limit-service**.

### Setup Spring-Cloud-Config-Server

### > **Added Dependency**

* Spring-loud-config-server
* spring-boot-devtools

### > **Enable Cloud Server Annotation**

first of to do that is add annotation **@EnableConfigServer** in SpringBootApplication class which is given below : 

	@SpringBootApplication
	@EnableConfigServer
	public class SpringCloudConfigServerApplication {
	
		public static void main(String[] args) {
			SpringApplication.run(SpringCloudConfigServerApplication.class, args);
		}
	}


### > application.yml

In this project I am using application.yml but you can use either application.yml or application.properties as your comfort.

You have to define application name as spring-cloud-config-server and set the port as your choice.

	spring:
	    application:
	        name: spring-cloud-config-server
	        
	server:
	    port: 8888
