package com.paul.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.dao.CustomerDao;
import com.paul.model.Customer;
import com.paul.service.CustomerManager;

@Service("customerManager")
@WebService(serviceName = "CustomerService", endpointInterface = "com.paul.service.CustomerManager")
public class CustomerManagerImpl extends GenericManagerImpl<Customer, Long> implements CustomerManager {
    CustomerDao customerDao;

    @Autowired
    public CustomerManagerImpl(CustomerDao customerDao) {
        super(customerDao);
        this.customerDao = customerDao;
    }
}