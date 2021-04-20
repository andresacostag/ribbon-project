package com.challenge.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class IpAnalyzerEurekaServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(IpAnalyzerEurekaServerApplication.class, args);
	}

}
