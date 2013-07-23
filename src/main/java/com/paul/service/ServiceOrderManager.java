package com.paul.service;

import javax.jws.WebService;

import com.paul.model.ServiceOrder;

@WebService
public interface ServiceOrderManager extends GenericManager<ServiceOrder, Long> {
    
}