package com.paul.service;

import javax.jws.WebService;

import com.paul.model.Accessories;

@WebService
public interface AccessoriesManager extends GenericManager<Accessories, Long> {
    
}