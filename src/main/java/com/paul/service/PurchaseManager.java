package com.paul.service;

import javax.jws.WebService;

import com.paul.model.Purchase;

@WebService
public interface PurchaseManager extends GenericManager<Purchase, Long> {
    
}