package com.challenge.ipanalyzer.services;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import com.challenge.ipanalyzer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public BlacklistedIpAddress create(BlacklistedIpAddress customer) throws Exception
    {
        try {
            customer.setCreationDate(new Date());
            BlacklistedIpAddress customerSaved = customerRepository.save(customer);
            return customerSaved;
        } catch(Exception e ) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }
    public BlacklistedIpAddress findById(Long customerId) throws Exception
    {
        try {
            Optional<BlacklistedIpAddress> customerOptional = customerRepository.findById(customerId);
            if(customerOptional.isPresent())
                return customerOptional.get();
            else
                throw new Exception("The resource you were trying to reach is not found");
        } catch(Exception e ) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }
    public List<BlacklistedIpAddress> findByName(String customerName) throws Exception
    {
        try  {
            List<BlacklistedIpAddress> customerList = customerRepository.findByName(customerName);
            if(!Objects.isNull(customerList))
                return customerList;
            else
                throw new Exception("The resource you were trying to reach is not found");
        } catch(Exception e ) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }
    public List<BlacklistedIpAddress> findAll() throws Exception
    {
        try {
            List<BlacklistedIpAddress> customerList = customerRepository.findAll();
            if(!Objects.isNull(customerList))
                return customerList;
            else
                throw new Exception("The resource you were trying to reach is not found");
        } catch(Exception e ) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }
}
