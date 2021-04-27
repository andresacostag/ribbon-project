package com.challenge.ipanalyzer.services;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import com.challenge.ipanalyzer.model.IpAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IpAddressServiceTest {

    @InjectMocks
    IpAddressService ipAddressService;

    @Mock
    BlacklistedIpAddressService blacklistedIpAddressService;

    @Mock
    RestTemplate restTemplate;

    @Test
    public void findIpAddressCountryTest() throws Exception {
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIpAddress("5.6.7.8");

        when( restTemplate.getForObject("https://api.ip2country.info/ip?5.6.7.8" , String.class)).thenReturn("{\"countryCode\": \"DE\",\"countryCode3\": \"DEU\",\"countryName\": \"Germany\",\"countryEmoji\": \"\uD83C\uDDE9\uD83C\uDDEA\"}");
        ipAddressService.findIpAddressCountry(ipAddress);

        Assertions.assertEquals("DE", ipAddress.getISOCode());
        Assertions.assertEquals("Germany", ipAddress.getCountry());
    }

    @Test
    public void findIpAddressCountryCurrencyTest() throws Exception {
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIpAddress("5.6.7.8");
        ipAddress.setCountry("Germany");
        ipAddress.setISOCode("DE");

        when( restTemplate.getForObject("https://restcountries.eu/rest/v2/alpha/DE", String.class)).thenReturn("{ \"currencies\": [{\"code\": \"EUR\",\"name\": \"Euro\",\"symbol\": \"€\"}]}");
        ipAddressService.findIpAddressCountryCurrency(ipAddress);

        Assertions.assertEquals("Euro", ipAddress.getLocalCurrency());
        Assertions.assertEquals("EUR", ipAddress.getLocalCurrencyCode());
    }

    @Test
    public void findCurrencyExchangeRateTest() throws Exception {
        IpAddress ipAddress = new IpAddress();
        ipAddress.setIpAddress("5.6.7.8");
        ipAddress.setLocalCurrencyCode("EUR");
        ReflectionTestUtils.setField(ipAddressService, "fixerKey", "569ace94d39d6190384185c64f58b3ff");

        when( restTemplate.getForObject("http://data.fixer.io/api/latest?access_key=569ace94d39d6190384185c64f58b3ff&symbols=EUR", String.class)).thenReturn("{\"rates\": {\"EUR\": 1}}");
        ipAddressService.findCurrencyExchangeRate(ipAddress);

        Assertions.assertEquals(1F, ipAddress.getExchangeRateEUR());
    }

    @Test
    public void forbiddenIpTest() throws Exception {
        List<BlacklistedIpAddress> list = new ArrayList<>();
        BlacklistedIpAddress ipOne = new BlacklistedIpAddress(1L, "192.168.0.1");
        list.add(ipOne);

        when(blacklistedIpAddressService.findByIpAddress("192.168.0.1")).thenReturn(list);

        ResponseEntity<Object> entity =  ipAddressService.findIpAddressInfo("5.6.7.8", "192.168.0.1");

        Assertions.assertEquals(entity.getStatusCode(), HttpStatus.FORBIDDEN);
    }
}
