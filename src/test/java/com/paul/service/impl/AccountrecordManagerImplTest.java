package com.paul.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paul.dao.AccountrecordDao;
import com.paul.model.Accountrecord;
import com.paul.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountrecordManagerImplTest extends BaseManagerMockTestCase {
    private AccountrecordManagerImpl manager = null;
    private AccountrecordDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(AccountrecordDao.class);
        manager = new AccountrecordManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetAccountrecord() {
        log.debug("testing get...");

        final Long id = 7L;
        final Accountrecord accountrecord = new Accountrecord();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(accountrecord));
        }});

        Accountrecord result = manager.get(id);
        assertSame(accountrecord, result);
    }

    @Test
    public void testGetAccountrecords() {
        log.debug("testing getAll...");

        final List accountrecords = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(accountrecords));
        }});

        List result = manager.getAll();
        assertSame(accountrecords, result);
    }

    @Test
    public void testSaveAccountrecord() {
        log.debug("testing save...");

        final Accountrecord accountrecord = new Accountrecord();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(accountrecord)));
        }});

        manager.save(accountrecord);
    }

    @Test
    public void testRemoveAccountrecord() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}