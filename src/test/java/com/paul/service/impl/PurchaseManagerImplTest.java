package com.paul.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paul.dao.PurchaseDao;
import com.paul.model.Purchase;
import com.paul.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class PurchaseManagerImplTest extends BaseManagerMockTestCase {
    private PurchaseManagerImpl manager = null;
    private PurchaseDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(PurchaseDao.class);
        manager = new PurchaseManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetPurchase() {
        log.debug("testing get...");

        final Long id = 7L;
        final Purchase purchase = new Purchase();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(purchase));
        }});

        Purchase result = manager.get(id);
        assertSame(purchase, result);
    }

    @Test
    public void testGetPurchases() {
        log.debug("testing getAll...");

        final List purchases = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(purchases));
        }});

        List result = manager.getAll();
        assertSame(purchases, result);
    }

    @Test
    public void testSavePurchase() {
        log.debug("testing save...");

        final Purchase purchase = new Purchase();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(purchase)));
        }});

        manager.save(purchase);
    }

    @Test
    public void testRemovePurchase() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}