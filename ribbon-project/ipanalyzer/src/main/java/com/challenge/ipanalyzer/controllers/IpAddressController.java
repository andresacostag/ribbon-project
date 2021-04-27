package com.challenge.ipanalyzer.controllers;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import com.challenge.ipanalyzer.model.IpAddress;
import com.challenge.ipanalyzer.services.BlacklistedIpAddressService;
import com.challenge.ipanalyzer.services.IpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ipaddress")
public class IpAddressController {

    @Autowired
    private IpAddressService ipAddressService;

    @CrossOrigin(origins = "*")
    @GetMapping("/info")
    public @ResponseBody ResponseEntity<Object> getIpInfo(@RequestParam(value = "ip") String ip, @RequestParam(value = "requestIp") String requestIp) {

        try {

            return ipAddressService.findIpAddressInfo(ip, requestIp);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
