package com.challenge.ipanalyzer.repositories;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlacklistedIpAddressRepository extends CrudRepository<BlacklistedIpAddress, Long> {

    List<BlacklistedIpAddress> findByIpAddress(String ipAddress);
    BlacklistedIpAddress findById(long id);
    List<BlacklistedIpAddress> findAll();
    void deleteById(Long id);
}