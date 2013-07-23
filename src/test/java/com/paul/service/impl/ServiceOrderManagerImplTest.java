package com.paul.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paul.dao.ServiceOrderDao;
import com.paul.model.ServiceOrder;
import com.paul.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class ServiceOrderManagerImplTest extends BaseManagerMockTestCase {
    private ServiceOrderManagerImpl manager = null;
    private ServiceOrderDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(ServiceOrderDao.class);
        manager = new ServiceOrderManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetServiceOrder() {
        log.debug("testing get...");

        final Long id = 7L;
        final ServiceOrder serviceOrder = new ServiceOrder();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(serviceOrder));
        }});

        ServiceOrder result = manager.get(id);
        assertSame(serviceOrder, result);
    }

    @Test
    public void testGetServiceOrders() {
        log.debug("testing getAll...");

        final List serviceOrders = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(serviceOrders));
        }});

        List result = manager.getAll();
        assertSame(serviceOrders, result);
    }

    @Test
    public void testSaveServiceOrder() {
        log.debug("testing save...");

        final ServiceOrder serviceOrder = new ServiceOrder();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(serviceOrder)));
        }});

        manager.save(serviceOrder);
    }

    @Test
    public void testRemoveServiceOrder() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}