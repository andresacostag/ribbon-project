package com.challenge.ipanalyzer.services;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import com.challenge.ipanalyzer.repositories.BlacklistedIpAddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlacklistedIpAddressServiceTest {

    @InjectMocks
    BlacklistedIpAddressService blacklistedIpAddressService;

    @Mock
    BlacklistedIpAddressRepository blacklistedIpAddressRepository;

    @Test
    public void createTest() throws Exception   {
        BlacklistedIpAddress ip = new BlacklistedIpAddress(1L,"192.168.0.1");

        blacklistedIpAddressService.create(ip);

        verify(blacklistedIpAddressRepository, times(1)).save(ip);
    }

    @Test
    public void deleteTest() throws Exception{
        BlacklistedIpAddress ipAddress = new BlacklistedIpAddress(1L,"192.168.0.1");

        blacklistedIpAddressService.delete(ipAddress.getId());
        verify(blacklistedIpAddressRepository).deleteById(ipAddress.getId());
    }

    @Test
    public void findByIpAddressTest() throws Exception {
        when(blacklistedIpAddressRepository.findByIpAddress("192.168.0.1")).thenReturn(Arrays.asList(new BlacklistedIpAddress(1L, "192.168.0.1")));

        List<BlacklistedIpAddress> ipAddresses = blacklistedIpAddressService.findByIpAddress("192.168.0.1");

        Assertions.assertEquals(1L, ipAddresses.get(0).getId());
        Assertions.assertEquals("192.168.0.1", ipAddresses.get(0).getIpAddress());
    }

    @Test
    public void findAllTest() throws Exception{
        List<BlacklistedIpAddress> list = new ArrayList<>();
        BlacklistedIpAddress ipOne = new BlacklistedIpAddress(1L, "192.168.0.1");
        BlacklistedIpAddress ipTwo = new BlacklistedIpAddress(2L, "192.168.0.2");
        BlacklistedIpAddress ipThree = new BlacklistedIpAddress(3L, "192.168.0.3");

        list.add(ipOne);
        list.add(ipTwo);
        list.add(ipThree);

        when(blacklistedIpAddressRepository.findAll()).thenReturn(list);

        List<BlacklistedIpAddress> ipList = blacklistedIpAddressService.findAll();

        Assertions.assertEquals(3, ipList.size());
        verify(blacklistedIpAddressRepository, times(1)).findAll();
    }

}
