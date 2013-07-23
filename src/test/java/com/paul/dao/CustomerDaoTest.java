package com.paul.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.paul.model.Customer;

public class CustomerDaoTest extends BaseDaoTestCase {
     @Autowired
    private CustomerDao customerDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveCustomer() {
        Customer customer = new Customer();

        // enter all required fields

        log.debug("adding customer...");
        customer = customerDao.save(customer);

        customer = customerDao.get(customer.getId());

        assertNotNull(customer.getId());

        log.debug("removing customer...");

        customerDao.remove(customer.getId());

        // should throw DataAccessException 
        customerDao.get(customer.getId());
    }
}