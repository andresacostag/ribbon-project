package com.challenge.ribbon.controllers;

import com.challenge.ribbon.model.BlacklistedIpAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyClientSideController {
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/client/frontend")
    public String hi() {

        String randomString = this.restTemplate.getForObject("http://server/backend", String.class);
        return "Server Response :: " + randomString;
    }

    @RequestMapping("/client/ipaddress")
    @PostMapping
    public @ResponseBody ResponseEntity<BlacklistedIpAddressDTO> createBlacklistedIpAddress(@RequestBody BlacklistedIpAddressDTO blacklistedIpAddress){

        BlacklistedIpAddressDTO result = restTemplate.postForObject( "http://server/blacklistedipaddress", blacklistedIpAddress, BlacklistedIpAddressDTO.class);

        return new ResponseEntity<BlacklistedIpAddressDTO>(result, HttpStatus.OK);
    }

}
