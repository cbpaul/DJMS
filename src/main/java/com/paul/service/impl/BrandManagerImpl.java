package com.paul.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.dao.BrandDao;
import com.paul.model.Brand;
import com.paul.service.BrandManager;

@Service("brandManager")
@WebService(serviceName = "BrandService", endpointInterface = "com.paul.service.BrandManager")
public class BrandManagerImpl extends GenericManagerImpl<Brand, Long> implements BrandManager {
    BrandDao brandDao;

    @Autowired
    public BrandManagerImpl(BrandDao brandDao) {
        super(brandDao);
        this.brandDao = brandDao;
    }
}