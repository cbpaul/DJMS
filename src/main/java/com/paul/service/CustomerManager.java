package com.paul.service;

import javax.jws.WebService;

import com.paul.model.Customer;

@WebService
public interface CustomerManager extends GenericManager<Customer, Long> {
    
}