package com.paul.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.paul.dao.EmployeeDao;
import com.paul.model.Employee;
import com.paul.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeManagerImplTest extends BaseManagerMockTestCase {
    private EmployeeManagerImpl manager = null;
    private EmployeeDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(EmployeeDao.class);
        manager = new EmployeeManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetEmployee() {
        log.debug("testing get...");

        final Long id = 7L;
        final Employee employee = new Employee();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(employee));
        }});

        Employee result = manager.get(id);
        assertSame(employee, result);
    }

    @Test
    public void testGetEmployees() {
        log.debug("testing getAll...");

        final List employees = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(employees));
        }});

        List result = manager.getAll();
        assertSame(employees, result);
    }

    @Test
    public void testSaveEmployee() {
        log.debug("testing save...");

        final Employee employee = new Employee();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(employee)));
        }});

        manager.save(employee);
    }

    @Test
    public void testRemoveEmployee() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}