package com.challenge.ipanalyzer.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BlacklistedIpAddress {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String ipAddress;

    private Date creationDate;

    public BlacklistedIpAddress() {}

    public BlacklistedIpAddress(Long id, String ipAddress) {
        this.id = id;
        this.ipAddress = ipAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return String.format(
                "IpAddress[id=%d, Ip='%s']",
                id, ipAddress);
    }
}
