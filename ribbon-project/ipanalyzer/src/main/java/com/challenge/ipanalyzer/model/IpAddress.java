package com.challenge.ipanalyzer.model;

public class IpAddress {

    private String ipAddress;

    private String country;

    private String ISOCode;

    private String localCurrency;

    private String localCurrencyCode;

    private Float exchangeRateEUR;

    public String getIpAddress() {

        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {

        this.ipAddress = ipAddress;
    }

    public String getCountry() {

        return country;
    }

    public void setCountry(String country) {

        this.country = country;
    }

    public String getISOCode() {

        return ISOCode;
    }

    public void setISOCode(String ISOCode) {

        this.ISOCode = ISOCode;
    }

    public String getLocalCurrency() {

        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {

        this.localCurrency = localCurrency;
    }

    public String getLocalCurrencyCode() {

        return localCurrencyCode;
    }

    public void setLocalCurrencyCode(String localCurrencyCode) {

        this.localCurrencyCode = localCurrencyCode;
    }

    public Float getExchangeRateEUR() {

        return exchangeRateEUR;
    }

    public void setExchangeRateEUR(Float exchangeRateEUR) {

        this.exchangeRateEUR = exchangeRateEUR;
    }
}
