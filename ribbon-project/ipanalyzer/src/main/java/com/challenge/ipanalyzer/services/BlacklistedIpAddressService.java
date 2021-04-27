package com.challenge.ipanalyzer.services;

import com.challenge.ipanalyzer.model.BlacklistedIpAddress;
import com.challenge.ipanalyzer.repositories.BlacklistedIpAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BlacklistedIpAddressService {

    @Autowired
    private BlacklistedIpAddressRepository ipAddressRepository;

    public BlacklistedIpAddress create(BlacklistedIpAddress blacklistedIpAddress) throws Exception {

        try {

            blacklistedIpAddress.setCreationDate(new Date());
            BlacklistedIpAddress blacklistedIpAddressSaved = ipAddressRepository.save(blacklistedIpAddress);
            return blacklistedIpAddressSaved;
        } catch(Exception e ) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }

    public void delete(Long id) throws Exception {

        try {

            ipAddressRepository.deleteById(id);
        } catch(Exception e ) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }

    public List<BlacklistedIpAddress> findByIpAddress(String ipAddress) throws Exception {

        try  {
            List<BlacklistedIpAddress> blacklistedIpAddressList = ipAddressRepository.findByIpAddress(ipAddress);
            if (!Objects.isNull(blacklistedIpAddressList)) {

                return blacklistedIpAddressList;
            } else {

                throw new Exception("The resource you were trying to reach is not found");
            }
        } catch(Exception e ) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }

    public List<BlacklistedIpAddress> findAll() throws Exception {

        try {
            List<BlacklistedIpAddress> blacklistedIpAddressList = ipAddressRepository.findAll();
            if (!Objects.isNull(blacklistedIpAddressList)) {

                return blacklistedIpAddressList;
            } else {

                throw new Exception("The resource you were trying to reach is not found");
            }
        } catch(Exception e ) {

            throw new Exception("The resource you were trying to reach is not found");
        }
    }
}
