package com.challenge.ipanalyzer.controllers;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import com.challenge.ipanalyzer.services.BlacklistedIpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blacklistedipaddress")
public class BlacklistedIpAddressController {

    @Autowired
    private BlacklistedIpAddressService blacklistedIpAddressService;

    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody ResponseEntity<Object> createBlacklistedIpAddress(@RequestBody BlacklistedIpAddress blacklistedIpAddress) {

        try {

            BlacklistedIpAddress response = blacklistedIpAddressService.create(blacklistedIpAddress);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/{id}")
    public @ResponseBody ResponseEntity<Boolean> deleteBlacklistedIpAddress(@PathVariable Long id) {

        try {

            blacklistedIpAddressService.delete(id);

            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public @ResponseBody ResponseEntity<Object> getAll() {

        try {

            List<BlacklistedIpAddress> response = blacklistedIpAddressService.findAll();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
