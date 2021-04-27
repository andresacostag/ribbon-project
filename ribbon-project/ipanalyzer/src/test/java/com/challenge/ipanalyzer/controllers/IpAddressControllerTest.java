package com.challenge.ipanalyzer.controllers;

import com.challenge.ipanalyzer.model.IpAddress;
import com.challenge.ipanalyzer.services.IpAddressService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@WebMvcTest(IpAddressController.class)
public class IpAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IpAddressService ipAddressService;

    @Test
    public void infoShouldReturnIpAddressInformation() throws Exception {
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIpAddress("5.6.7.8");
        ipAddress.setCountry("Germany");
        ipAddress.setISOCode("DE");
        ipAddress.setLocalCurrencyCode("EUR");
        ipAddress.setLocalCurrency("Euro");
        ipAddress.setExchangeRateEUR(1F);

        ResponseEntity<Object> entity = new ResponseEntity<>(ipAddress, HttpStatus.OK);
        when(ipAddressService.findIpAddressInfo("5.6.7.8", "192.168.0.1")).thenReturn(entity);
        this.mockMvc.perform(get("/ipaddress/info?ip=5.6.7.8&requestIp=192.168.0.1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"country\":\"Germany\"")))
                .andExpect(content().string(containsString("\"localCurrency\":\"Euro\"")))
                .andExpect(content().string(containsString("\"localCurrencyCode\":\"EUR\"")))
                .andExpect(content().string(containsString("\"exchangeRateEUR\":1.0")))
                .andExpect(content().string(containsString("\"isocode\":\"DE\"")));
    }
}
