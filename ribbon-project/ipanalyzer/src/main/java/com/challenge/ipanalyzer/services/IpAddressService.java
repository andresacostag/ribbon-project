package com.challenge.ipanalyzer.services;

import com.challenge.ipanalyzer.model.IpAddress;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class IpAddressService {

    @Value("${fixer.io.key}")
    private String fixerKey;

    @Bean
    RestTemplate restTemplate() {

        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    BlacklistedIpAddressService blacklistedIpAddressService;

    public String helloWorld(){
        return "Hello";
    }

    public IpAddress findIpAddressCountry(IpAddress ipAddressDTO) throws Exception {

        try {

            String json = this.restTemplate.getForObject("https://api.ip2country.info/ip?" + ipAddressDTO.getIpAddress(), String.class);
            JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

            ipAddressDTO.setCountry(jsonObject.get("countryName").getAsString());
            ipAddressDTO.setISOCode(jsonObject.get("countryCode").getAsString());

            return ipAddressDTO;

        } catch (Exception e) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }

    public IpAddress findIpAddressCountryCurrency(IpAddress ipAddressDTO) throws Exception {

        try {

            String json = this.restTemplate.getForObject("https://restcountries.eu/rest/v2/alpha/" + ipAddressDTO.getISOCode(), String.class);
            JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

            JsonArray currencies = jsonObject.getAsJsonArray("currencies");
            JsonObject jsonCurrency = new Gson().fromJson(currencies.get(0), JsonObject.class);

            ipAddressDTO.setLocalCurrency(jsonCurrency.get("name").getAsString());
            ipAddressDTO.setLocalCurrencyCode(jsonCurrency.get("code").getAsString());

            return ipAddressDTO;

        } catch (Exception e) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }

    public IpAddress findCurrencyExchangeRate(IpAddress ipAddressDTO) throws Exception {

        try {

            String json = this.restTemplate.getForObject("http://data.fixer.io/api/latest?access_key=" + fixerKey + "&symbols=" + ipAddressDTO.getLocalCurrencyCode(), String.class);
            JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);

            JsonObject rates = jsonObject.getAsJsonObject("rates");

            ipAddressDTO.setExchangeRateEUR(rates.get(ipAddressDTO.getLocalCurrencyCode()).getAsFloat());

            return ipAddressDTO;

        } catch (Exception e) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }

    public ResponseEntity<Object>  findIpAddressInfo(String ipAddress, String requestIpAddress) throws Exception {

        if (blacklistedIpAddressService.findByIpAddress(requestIpAddress).isEmpty()) {

            IpAddress ipAddressDTO = new IpAddress();
            ipAddressDTO.setIpAddress(ipAddress);
            findIpAddressCountry(ipAddressDTO);
            findIpAddressCountryCurrency(ipAddressDTO);
            findCurrencyExchangeRate(ipAddressDTO);

            return new ResponseEntity<>(ipAddressDTO, HttpStatus.OK);
        } else {

            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
        }

    }
}
