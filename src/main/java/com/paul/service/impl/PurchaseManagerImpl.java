package com.paul.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.dao.PurchaseDao;
import com.paul.model.Purchase;
import com.paul.service.PurchaseManager;

@Service("purchaseManager")
@WebService(serviceName = "PurchaseService", endpointInterface = "com.paul.service.PurchaseManager")
public class PurchaseManagerImpl extends GenericManagerImpl<Purchase, Long> implements PurchaseManager {
    PurchaseDao purchaseDao;

    @Autowired
    public PurchaseManagerImpl(PurchaseDao purchaseDao) {
        super(purchaseDao);
        this.purchaseDao = purchaseDao;
    }
}