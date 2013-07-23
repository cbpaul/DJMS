package com.paul.service;

import javax.jws.WebService;

import com.paul.model.Brand;

@WebService
public interface BrandManager extends GenericManager<Brand, Long> {
    
}