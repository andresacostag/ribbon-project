package com.challenge.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IpanalyzerRibbonServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(IpanalyzerRibbonServerApplication.class, args);
	}

}
