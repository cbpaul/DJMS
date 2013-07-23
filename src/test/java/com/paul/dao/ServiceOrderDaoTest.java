package com.paul.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.paul.model.ServiceOrder;

public class ServiceOrderDaoTest extends BaseDaoTestCase {
     @Autowired
    private ServiceOrderDao serviceOrderDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveServiceOrder() {
        ServiceOrder serviceOrder = new ServiceOrder();

        // enter all required fields

        log.debug("adding serviceOrder...");
        serviceOrder = serviceOrderDao.save(serviceOrder);

        serviceOrder = serviceOrderDao.get(serviceOrder.getId());

        assertNotNull(serviceOrder.getId());

        log.debug("removing serviceOrder...");

        serviceOrderDao.remove(serviceOrder.getId());

        // should throw DataAccessException 
        serviceOrderDao.get(serviceOrder.getId());
    }
}