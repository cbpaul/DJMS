package com.paul.dao;

import com.paul.dao.GenericDao;

import com.paul.model.Employee;

/**
 * An interface that provides a data management interface to the Employee table.
 */
public interface EmployeeDao extends GenericDao<Employee, Long> {

}