package com.challenge.ipanalyzer.controllers;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import com.challenge.ipanalyzer.services.BlacklistedIpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/customer")
public class CustomerController {

    @Autowired
    private BlacklistedIpAddressService blacklistedIpAddressService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody ResponseEntity<Object> createBlacklistedIpAddress(@RequestBody BlacklistedIpAddress blacklistedIpAddress) {
        try {

            BlacklistedIpAddress response = blacklistedIpAddressService.create(blacklistedIpAddress);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/extended/{customerName}")
    public @ResponseBody ResponseEntity<Object> getByName(@PathVariable(name = "ipAddress", required = true) String blacklistedIpAddress) {
        try {

            List<BlacklistedIpAddress> response = blacklistedIpAddressService.findByIpAddress(blacklistedIpAddress);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping
    public @ResponseBody ResponseEntity<Object> getAll() {
        try {
            List<BlacklistedIpAddress> response = blacklistedIpAddressService.findAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
