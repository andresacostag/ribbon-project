package com.challenge.ribbon;

import com.challenge.ribbon.configuration.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@RibbonClient(name = "server", configuration = RibbonConfiguration.class)
public class IpAnalyzerRibbonServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(IpAnalyzerRibbonServerApplication.class, args);
	}

}
