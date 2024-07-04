package com.bootcamp.ws.soap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.ws.soap.client.CountryClient;

import com.bootcamp.ws.soap.wsdl.GetCountryRequest;
import com.bootcamp.ws.soap.wsdl.GetCountryResponse;
import com.bootcamp.ws.soap.wsdl.Country;

@RestController
public class CountryController {
    @Autowired
    CountryClient client;

    @PostMapping("/api/country")
    public ResponseEntity<Country> sendEmail(@RequestBody GetCountryRequest request) throws Exception{

        GetCountryResponse response = client.getCountry(request.getName());
        return ResponseEntity.ok(response.getCountry());
    }
}