package com.paul.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paul.dao.CustomerDao;
import com.paul.model.Customer;
import com.paul.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerManagerImplTest extends BaseManagerMockTestCase {
    private CustomerManagerImpl manager = null;
    private CustomerDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(CustomerDao.class);
        manager = new CustomerManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetCustomer() {
        log.debug("testing get...");

        final Long id = 7L;
        final Customer customer = new Customer();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(customer));
        }});

        Customer result = manager.get(id);
        assertSame(customer, result);
    }

    @Test
    public void testGetCustomers() {
        log.debug("testing getAll...");

        final List customers = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(customers));
        }});

        List result = manager.getAll();
        assertSame(customers, result);
    }

    @Test
    public void testSaveCustomer() {
        log.debug("testing save...");

        final Customer customer = new Customer();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(customer)));
        }});

        manager.save(customer);
    }

    @Test
    public void testRemoveCustomer() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}