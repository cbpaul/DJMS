package com.paul.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paul.dao.AccessoriesDao;
import com.paul.model.Accessories;
import com.paul.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccessoriesManagerImplTest extends BaseManagerMockTestCase {
    private AccessoriesManagerImpl manager = null;
    private AccessoriesDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(AccessoriesDao.class);
        manager = new AccessoriesManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetAccessories() {
        log.debug("testing get...");

        final Long id = 7L;
        final Accessories accessories = new Accessories();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(accessories));
        }});

        Accessories result = manager.get(id);
        assertSame(accessories, result);
    }

    @Test
    public void testGetAccessoriess() {
        log.debug("testing getAll...");

        final List accessoriess = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(accessoriess));
        }});

        List result = manager.getAll();
        assertSame(accessoriess, result);
    }

    @Test
    public void testSaveAccessories() {
        log.debug("testing save...");

        final Accessories accessories = new Accessories();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(accessories)));
        }});

        manager.save(accessories);
    }

    @Test
    public void testRemoveAccessories() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}