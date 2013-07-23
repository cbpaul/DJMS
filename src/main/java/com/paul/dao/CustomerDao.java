package com.paul.dao;

import com.paul.dao.GenericDao;

import com.paul.model.Customer;

/**
 * An interface that provides a data management interface to the Customer table.
 */
public interface CustomerDao extends GenericDao<Customer, Long> {

}