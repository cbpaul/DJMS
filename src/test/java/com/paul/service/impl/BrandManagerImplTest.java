package com.paul.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paul.dao.BrandDao;
import com.paul.model.Brand;
import com.paul.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class BrandManagerImplTest extends BaseManagerMockTestCase {
    private BrandManagerImpl manager = null;
    private BrandDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(BrandDao.class);
        manager = new BrandManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetBrand() {
        log.debug("testing get...");

        final Long id = 7L;
        final Brand brand = new Brand();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(brand));
        }});

        Brand result = manager.get(id);
        assertSame(brand, result);
    }

    @Test
    public void testGetBrands() {
        log.debug("testing getAll...");

        final List brands = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(brands));
        }});

        List result = manager.getAll();
        assertSame(brands, result);
    }

    @Test
    public void testSaveBrand() {
        log.debug("testing save...");

        final Brand brand = new Brand();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(brand)));
        }});

        manager.save(brand);
    }

    @Test
    public void testRemoveBrand() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}