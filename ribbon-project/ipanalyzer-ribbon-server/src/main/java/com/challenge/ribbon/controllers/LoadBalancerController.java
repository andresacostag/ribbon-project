package com.challenge.ribbon.controllers;

import com.challenge.ribbon.model.BlacklistedIpAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/client")
public class LoadBalancerController {
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/frontend")
    public String hi() {

        String randomString = this.restTemplate.getForObject("http://server/backend", String.class);
        return "Server Response :: " + randomString;
    }

    @PostMapping("/blacklistedipaddress")
    public @ResponseBody ResponseEntity<Object> createBlacklistedIpAddress(@RequestBody BlacklistedIpAddressDTO blacklistedIpAddress){
        try {

            Object result = restTemplate.postForObject( "http://server/blacklistedipaddress", blacklistedIpAddress, Object.class);

            return new ResponseEntity<Object>(result, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/ipaddress")
    public @ResponseBody ResponseEntity<Object> findIpAddressInfo(@RequestParam(value = "ip") String ip){

        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            String remoteAddress = request.getRemoteAddr();

            Object response =  this.restTemplate.getForObject("http://server/ipaddress/info?ip=" + ip + "&requestIp="+remoteAddress, Object.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
