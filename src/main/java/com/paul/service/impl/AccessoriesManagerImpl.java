package com.paul.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.dao.AccessoriesDao;
import com.paul.model.Accessories;
import com.paul.service.AccessoriesManager;

@Service("accessoriesManager")
@WebService(serviceName = "AccessoriesService", endpointInterface = "com.paul.service.AccessoriesManager")
public class AccessoriesManagerImpl extends GenericManagerImpl<Accessories, Long> implements AccessoriesManager {
    AccessoriesDao accessoriesDao;
    @Autowired
    public AccessoriesManagerImpl(AccessoriesDao accessoriesDao) {
        super(accessoriesDao);
        this.accessoriesDao = accessoriesDao;
    }
}