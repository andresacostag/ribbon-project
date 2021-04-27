package com.challenge.ipanalyzer.controllers;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import com.challenge.ipanalyzer.services.BlacklistedIpAddressService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlacklistedIpAddressController.class)
public class BlacklistedIpAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlacklistedIpAddressService blacklistedIpAddressService;

    @Test
    public void createBlacklistedIpAddressAPI() throws Exception
    {
        when(blacklistedIpAddressService.create(any(BlacklistedIpAddress.class))).thenReturn(new BlacklistedIpAddress(1L, "192.168.0.1"));

        Gson gson = new Gson();
        String json = gson.toJson(new BlacklistedIpAddress(null, "192.168.0.1"));

        this.mockMvc.perform( MockMvcRequestBuilders
                .post("/blacklistedipaddress")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.ipAddress").exists());
    }

    @Test
    public void deleteBlacklistedIpAddress() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/blacklistedipaddress/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllShouldReturnBlacklistedIpAddressesList() throws Exception {
        List<BlacklistedIpAddress> addresses = new ArrayList<>();
        BlacklistedIpAddress ipAddress1 = new BlacklistedIpAddress(1L,"192.168.0.1");
        BlacklistedIpAddress ipAddress2 = new BlacklistedIpAddress(1L,"192.168.0.2");
        addresses.add(ipAddress1);
        addresses.add(ipAddress2);

        when(blacklistedIpAddressService.findAll()).thenReturn(addresses);
        this.mockMvc.perform(get("/blacklistedipaddress/all")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("\"ipAddress\":\"192.168.0.1\"")))
                .andExpect(content().string(containsString("\"ipAddress\":\"192.168.0.2\"")));
    }

}
