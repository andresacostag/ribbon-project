package com.challenge.ipanalyzer.repositories;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<BlacklistedIpAddress, Long> {

    List<BlacklistedIpAddress> findByName(String name);
    BlacklistedIpAddress findById(long id);
    List<BlacklistedIpAddress> findAll();
}