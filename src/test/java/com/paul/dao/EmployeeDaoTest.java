package com.paul.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.ExpectedException;

import com.paul.model.Employee;

public class EmployeeDaoTest extends BaseDaoTestCase {
     @Autowired
    private EmployeeDao employeeDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveEmployee() {
        Employee employee = new Employee();

        // enter all required fields

        log.debug("adding employee...");
        employee = employeeDao.save(employee);

        employee = employeeDao.get(employee.getId());

        assertNotNull(employee.getId());

        log.debug("removing employee...");

        employeeDao.remove(employee.getId());

        // should throw DataAccessException 
        employeeDao.get(employee.getId());
    }
}